package com.example.appchitieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<User> {
    private int GroupID;
    private Context context;
    private List<User> users;
    public Adapter(Context context, int vg, List<User> users) {
        super(context, vg, users);
        this.GroupID = vg;
        this.context = context;
        this.users = users;
    }

    public class Viewholder {
        TextView tien;
        TextView loai;
        TextView diadiem;
        TextView thoigian;
        TextView ghichu;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Viewholder viewholder = new Viewholder();
        if (rowView == null) {
            rowView = LayoutInflater.from(context).inflate(GroupID, parent, false);
            viewholder.tien = rowView.findViewById(R.id.txt_chitieu);
            viewholder.loai = rowView.findViewById(R.id.txt_loaichitieu);
            viewholder.diadiem = rowView.findViewById(R.id.txt_diachi);
            viewholder.thoigian = rowView.findViewById(R.id.txt_date1);
            viewholder.ghichu = rowView.findViewById(R.id.txt_ghichu);
            rowView.setTag(viewholder);
        } else {
            viewholder = (Viewholder) rowView.getTag();
        }
        final User user = users.get(position);
        viewholder.tien.setText(String.valueOf(user.getSotienchitieu()));
        viewholder.loai.setText(user.getLoai());
        viewholder.diadiem.setText(user.getDiadiem());
        viewholder.thoigian.setText(user.getThoigian());
        viewholder.ghichu.setText(user.getGhichu());
        return rowView;
    }
}
