package com.example.thi1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddLocal extends AppCompatActivity {
    Button btnBack, btnOK;
    DBHelper dbh;
    EditText edtSoXe, edtQD, edtDonGia, edtKM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_local);
        btnBack = findViewById(R.id.btnBack);
        btnOK = findViewById(R.id.btnOK);
        edtDonGia = findViewById(R.id.edtDG);
        edtKM = findViewById(R.id.edtKM);
        edtQD = findViewById(R.id.edtQD);
        edtSoXe = findViewById(R.id.edtSoXe);
        dbh = new DBHelper(AddLocal.this);
        dbh.openDB();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtKM.getText().toString().equals("") || !edtSoXe.getText().toString().equals("") || !edtQD.getText().toString().equals("") || !edtDonGia.getText().toString().equals("")){
                    long x;
                    ArrayList<HoaDon> hd = dbh.getAll();
                    Double DG = Double.parseDouble(edtDonGia.getText().toString());
                    String sx = edtSoXe.getText().toString();
                    Double qd = Double.parseDouble(edtQD.getText().toString());
                    x = dbh.Insert(hd.size()+1, sx, qd , DG, Double.parseDouble(edtKM.getText().toString()));
                    if (x==-1){
                        Toast.makeText(AddLocal.this, "Fail", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddLocal.this, "Success", Toast.LENGTH_SHORT).show();
                        gettoback();
                    }
                }else{
                    Toast.makeText(AddLocal.this, "Vui lòng nhập đủ thong tin!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void gettoback(){
        Intent it = new Intent(AddLocal.this, MainActivity.class);
        startActivity(it);
    }
    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }
}