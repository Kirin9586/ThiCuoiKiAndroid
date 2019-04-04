package com.example.appchitieu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.appchitieu.Adapter;
import com.example.appchitieu.R;
import com.example.appchitieu.SQLite;
import com.example.appchitieu.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LichSuMuaHang extends Fragment {
    static SQLite helper;
    ListView listView;
    Adapter adapter;
    List<User> userList = new ArrayList<>();
    List<User> us = new ArrayList<>();
    @Override
    public void onResume() {
        super.onResume();
        us.clear();
        userList.clear();
        userList.addAll(helper.getAll());
        Date lo = new Date();
        try {
            lo = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().getTime().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < userList.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(userList.get(i).getThoigian());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDay.getMonth()< lo.getMonth()){
                us.add(userList.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_lich_su_mua_hang, container, false);
        helper = new SQLite(getContext());
        listView = view.findViewById(R.id.listki);
        userList.addAll(helper.getAll());
        if (userList.isEmpty()){
            userList = helper.getAll();
        }
        Date lo = new Date();
        try {
            lo = new SimpleDateFormat("dd/MM/yyyy").parse(Calendar.getInstance().getTime().toString());


        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < userList.size(); i++){
            Date getDay = new Date();
            try {
                getDay = new SimpleDateFormat("dd/MM/yyyy").parse(userList.get(i).getThoigian());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDay.getMonth()< lo.getMonth()){
                us.add(userList.get(i));
            }
            else{
            }
        }
        adapter = new Adapter(getContext(),R.layout.custom_khoan,us);
        listView.setAdapter(adapter);
        return view;
    }
}

