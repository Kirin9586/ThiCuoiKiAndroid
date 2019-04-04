package com.example.appchitieu;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appchitieu.fragment.LichSuMuaHang;
import com.example.appchitieu.fragment.PhieuChiFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLite sql;
    List<User> users = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView addsodu;
    TextView tongchi;
    SharedPreferences preferences;
    ImageView adddu;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onResume() {
        super.onResume();
        Date lo = new Date();
        try {
            lo = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().getTime().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            tongchi.setText(String.valueOf(sql.Sum(lo.getMonth(),lo.getYear())));
            String s3 =addsodu.getText().toString();
            int so3 = Integer.parseInt(s3);
            String s4 =tongchi.getText().toString();
            int so4 = Integer.parseInt(s4);
            int hieu = so3 -so4;
            addsodu.setText(String.valueOf(hieu));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onIt();
        tabLayout.setupWithViewPager(viewPager);
        Pagestate adapter = new Pagestate(getSupportFragmentManager());
        adapter.addFragment(new PhieuChiFragment(), "Phieu");
        adapter.addFragment(new LichSuMuaHang(), "LichSu");
        viewPager.setAdapter(adapter);
        addsodu = findViewById(R.id.txt_sotien);
        tongchi = findViewById(R.id.txt_tongchi);
        adddu = findViewById(R.id.add);
        sql = new SQLite(this);
        preferences = getSharedPreferences("save", Context.MODE_PRIVATE);
        final int ID = preferences.getInt("ID", 0);
        addsodu.setText(String.valueOf(sql.GetSodu(ID)));
        sql.Update(ID,addsodu.getText().toString());
        adddu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog);
                final EditText du = dialog.findViewById(R.id.edt_du);
                du.setText(String.valueOf(sql.GetSodu(ID)));
                Button btnOk = dialog.findViewById(R.id.btn_tongdu);
                Button btnCancel = dialog.findViewById(R.id.btn_cancel);
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s1 = addsodu.getText().toString();
                        int so1 = Integer.parseInt(s1);
                        String s2 = du.getText().toString();
                        int so2 = Integer.parseInt(s2);
                        int tong = so1 + so2;
                        addsodu.setText(String.valueOf(tong));
                        sql.Update(ID, addsodu.getText().toString());
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
    private void onIt() {
        tabLayout = findViewById(R.id.demo_mTabLayout);
        viewPager = findViewById(R.id.demo_mViewPager);
    }
}
