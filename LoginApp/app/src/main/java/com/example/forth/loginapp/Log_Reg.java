package com.example.forth.loginapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Log_Reg extends AppCompatActivity {

    EditText userName, passWord;
    Button login,reg;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__reg);
        init();
        listeners();
    }


    public void init(){
        userName = findViewById(R.id.E_txt_nev);
        passWord = findViewById(R.id.E_txt_jelszo);
        login = findViewById(R.id.Btn_login);
        reg = findViewById(R.id.Btn_reg);
        db = new DB(this);
    }

    public void listeners(){
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                user_login(userName.getText().toString(), passWord.getText().toString());
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regFormIntent = new Intent(Log_Reg.this, Reg_Ac.class);
                startActivity(regFormIntent);
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void user_login(String user, String pw){
        Cursor data = db.getData("UserTable");
        boolean user_exists = false;
        boolean pw_check = false;

        if (user.length() > 0 && pw.length() > 0) {
            if (data != null && data.getCount() > 0) {
                while (data.moveToNext()) {
                    if (Objects.equals(data.getString(1), user)) {
                        user_exists = true;
                        if (Objects.equals(data.getString(2), pw)) {
                            pw_check = true;
                        }
                    }
                }
            }
            if (user_exists && pw_check) {
                Intent userFormIntent = new Intent(Log_Reg.this, userData.class);
                startActivity(userFormIntent);
                finish();
            } else if (!user_exists) {
                Toast.makeText(this, "Hibás felhasználónév!", Toast.LENGTH_SHORT).show();
            } else if (!pw_check) {
                Toast.makeText(this, "Hibás jelszó!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Valami nagyon elbaszódott!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"A mezőket kötelező kitölteni!", Toast.LENGTH_SHORT).show();
        }
    }
}
