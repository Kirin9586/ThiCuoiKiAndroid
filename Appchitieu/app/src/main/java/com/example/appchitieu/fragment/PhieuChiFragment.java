package com.example.appchitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appchitieu.Adapter;
import com.example.appchitieu.R;
import com.example.appchitieu.SQLite;
import com.example.appchitieu.ThemphieuActivity;
import com.example.appchitieu.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PhieuChiFragment extends Fragment {
    public static SQLite helper;
    public static List<User> users = new ArrayList<>();
    ListView listView;
    List<User>us = new ArrayList<>();
    public  static Adapter adapter;
    Button btnadd;
    Button btnHis;
    TextView du;

    @Override
    public void onResume() {
        super.onResume();
        us.clear();
        users.clear();
        users.addAll(helper.getAll());
        Date lo = new Date();
        try {
            lo = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().getTime().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < users.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(users.get(i).getThoigian());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDay.getMonth()== lo.getMonth()){
                us.add(users.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.phieuchifragment, container, false);
        helper =new SQLite(getContext());
       listView = view.findViewById(R.id.view);
       btnadd = view.findViewById(R.id.btnadd);
       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(getContext(), ThemphieuActivity.class));
           }
       });
       users.addAll(helper.getAll());
       if(users.isEmpty()){
           users = helper.getAll();
       }
        Date lo = new Date();
        try {
            lo = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().getTime().toString());


        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < users.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(users.get(i).getThoigian());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDay.getMonth()== lo.getMonth()){
                us.add(users.get(i));
            }
            else{
            }
        }
        adapter = new Adapter(getContext(),R.layout.custom_khoan,us);
       listView.setAdapter(adapter);
        return view;
    }
}
