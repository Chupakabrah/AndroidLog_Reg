package com.example.forth.loginapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Reg_Ac extends AppCompatActivity {

    private EditText regUserName, regFullName, regPassWord, regPassWordChack;
    private Button registBtn;
    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_);
        init();
        listeners();
    }

    public void init(){
        regUserName = findViewById(R.id.E_txt_Fnev);
        regFullName = findViewById(R.id.E_txt_Tnev);
        regPassWord = findViewById(R.id.E_txt_jelszo);
        regPassWordChack = findViewById(R.id.E_txt_pwCheck);
        registBtn = findViewById(R.id.Btn_regist);
        db = new DB(this);
    }

    public void listeners(){
        registBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (regUserName.getText().toString().length() > 0 && regFullName.getText().toString().length() > 0 && regPassWord.getText().toString().length() > 0 && regPassWordChack.getText().toString().length() > 0){
                    if (Objects.equals(regPassWord.getText().toString(), regPassWordChack.getText().toString())){
                        if (db.Reg(regUserName.getText().toString(), regPassWord.getText().toString(), regFullName.getText().toString())){
                            Toast.makeText(Reg_Ac.this,"Sikeres Regisztráció!", Toast.LENGTH_SHORT).show();
                            Intent loginFormIntent = new Intent(Reg_Ac.this, Log_Reg.class);
                            startActivity(loginFormIntent);
                            finish();
                        }
                    }else{
                        Toast.makeText(Reg_Ac.this,"A megadott jelszavak nem eggyeznek!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Reg_Ac.this,"Mindent kötelező kitölteni!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
