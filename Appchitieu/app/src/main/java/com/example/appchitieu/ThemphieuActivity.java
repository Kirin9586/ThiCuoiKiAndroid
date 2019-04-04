package com.example.appchitieu;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.appchitieu.fragment.PhieuChiFragment;

import java.util.Calendar;

public class ThemphieuActivity extends AppCompatActivity {
    static SQLite helper;
    EditText sotien;
    Spinner spinner;
    EditText diadiem;
    EditText thoigian;
    EditText ghichu;
    Button save;
    String msotien;
    String mloai;
    String mdiadiem;
    String mthoigian;
    String mghichu;
    SharedPreferences preferences;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themphieu);
        onIt();
        setSpinner();
        onValidate();

        helper = new SQLite(this);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onValidate()){
                    helper.Insert(Integer.parseInt(msotien),mloai,mdiadiem,mthoigian,mghichu);
                    PhieuChiFragment.users.addAll(helper.getAll());
                    PhieuChiFragment.adapter.notifyDataSetChanged();
                    finish();
                }
            }
        });
        thoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                // date picker dialog
                datePickerDialog  = new DatePickerDialog(ThemphieuActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        thoigian.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private boolean onValidate() {
        msotien = sotien.getText().toString();
        if(sotien.getText().toString().length()<1){
            sotien.setError("Null ");
            return false;
        }
        mloai= spinner.getSelectedItem().toString();
        if(spinner.getSelectedItem().toString().length()<1){
            return false;
        }
        mdiadiem =diadiem.getText().toString();
        if(diadiem.getText().toString().length()<1){
            diadiem.setError("Null");
            return false;
        }
        mthoigian = thoigian.getText().toString();
        mghichu = ghichu.getText().toString();
        if (ghichu.getText().toString().length()<1){
            ghichu.setError("Null");
            return false;
        }
        return true;
    }

    private void onIt() {
        sotien = findViewById(R.id.edt_chitieu);
        spinner = findViewById(R.id.spiner);
        diadiem = findViewById(R.id.edt_diachi);
        thoigian = findViewById(R.id.txt_date);
        ghichu = findViewById(R.id.edt_ghichu);
        save = findViewById(R.id.btn_save);
    }
    void setSpinner() {
        Spinner spinner = findViewById(R.id.spiner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.item1,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
