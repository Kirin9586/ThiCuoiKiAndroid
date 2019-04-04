package com.example.appchitieu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static SQLite helper;
    EditText edttk;
    EditText edtmk;
    Button btnLogin;
    Button btnregister;
    int Id = 0;
    SharedPreferences share;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        helper = new SQLite(this);
        onIt();
        CheckLoged();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Valiable()) {
                    if (helper.CheckData(edttk.getText().toString(),edtmk.getText().toString())) {
                        Id = helper.GetIDLogin(edttk.getText().toString(), edtmk.getText().toString());
                        Toast.makeText(LoginActivity.this, "Dang nhap thanh cong", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        editor.putBoolean("login", true);
                        editor.putInt("ID", Id);
                        editor.apply();
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Conect Fail", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(LoginActivity.this, Register.class);
                startActivity(i);

            }
        });

    }
    private void onIt() {
        edttk = findViewById(R.id.edt_tk);
        edtmk = findViewById(R.id.edt_mk);
        btnLogin = findViewById(R.id.btn_login);
        btnregister = findViewById(R.id.btn_register);
       share = getSharedPreferences("save",Context.MODE_PRIVATE);
       editor = share.edit();
    }
    boolean Valiable(){
        if (edttk.getText().toString().equals("")){
            edttk.setError("Null");
            return false;
        }else  if(edtmk.getText().toString().equals("")){
            edtmk.setError("Null");
            return false;
        }else
            return true;
    }
    void CheckLoged(){
        if(share.getBoolean("login",false)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }
}
