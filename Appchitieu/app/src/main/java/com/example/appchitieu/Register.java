package com.example.appchitieu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText tk;
    EditText mk;
    EditText nhapmk;
    EditText sodu;
    Button regist;
   static SQLite helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        onIt();
        helper = new SQLite(this);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valuable()) {
                    if (mk.getText().toString().equals(nhapmk.getText().toString())) {
                       LoginActivity.helper.InsertLogin(tk.getText().toString(),mk.getText().toString(),Integer.parseInt(sodu.getText().toString()));
                        finish();
                    } else {
                        nhapmk.setError("Nhập lại éo đúng???");
                    }
                }
            }
        });
    }

    private void onIt() {
        tk  = findViewById(R.id.user);
        mk = findViewById(R.id.pass);
        nhapmk = findViewById(R.id.repass);
        sodu = findViewById(R.id.sodu);
        regist = findViewById(R.id.btn_loginrin);
    }
    Boolean valuable() {
        if (tk.getText().toString().equals("")) {
            tk.setError("Null");
            return false;
        } else if (mk.getText().toString().equals("")) {
            mk.setError("Null");
            return false;
        } else if (nhapmk.getText().toString().equals("")) {
            nhapmk.setError("Null");
            return false;
        }
        return true;
    }
}
