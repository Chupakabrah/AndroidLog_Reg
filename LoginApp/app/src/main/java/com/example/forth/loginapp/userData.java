package com.example.forth.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class userData extends AppCompatActivity {

    TextView udv;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        init();
        listeners();
    }

    public void init(){
        udv = findViewById(R.id.udvozlegyTxTV);
        logout = findViewById(R.id.Btn_logout);
        udv.setText("Üdvözöllek : ");
    }

    public void listeners(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userFormIntent = new Intent(userData.this,Log_Reg.class);
                startActivity(userFormIntent);
                finish();
            }
        });
    }
}
