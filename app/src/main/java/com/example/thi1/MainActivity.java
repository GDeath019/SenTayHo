package com.example.thi1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edt;
    FloatingActionButton fabAdd;
    ListView lv;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        dbh = new DBHelper(MainActivity.this);
        dbh.openDB();
        /////
        final ArrayList<restaurant> ktra = dbh.getAll();
        if(ktra.size()<1){
            add();
        }
        final ArrayList<restaurant> arrayList = dbh.getAll();
        final ResAdapter resAdapter = new ResAdapter(getApplicationContext(), arrayList);
        lv.setAdapter(resAdapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Delete this local!!!");
                alert.setMessage("Are you sure want to delete this?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        long x = dbh.Delete(arrayList.get(position).getMa());
                        arrayList.remove(position);
                        resAdapter.notifyDataSetChanged();
                        if(x==0){
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("No", null);
                alert.show();
                return true;
            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                resAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void add() {
        long x =0;
        x += dbh.Insert(1,"Sen Tây Hồ", "Lạc Long Quân", 8.8);
        Toast.makeText(this, "DONE1", Toast.LENGTH_LONG).show();
        x += dbh.Insert(2,"Nón Lá", "Nguyễn Đình Chiểu", 8.8);
        Toast.makeText(this, "DONE1", Toast.LENGTH_LONG).show();
        x += dbh.Insert(3,"Sen Tây Hồ", "Lạc Long Quân", 8.8);
        Toast.makeText(this, "DONE1", Toast.LENGTH_LONG).show();
        x += dbh.Insert(4,"Sen Tây Hồ", "Lạc Long Quân", 8.8);
        Toast.makeText(this, "DONE1", Toast.LENGTH_LONG).show();
    }

    private void AnhXa() {
        lv = findViewById(R.id.lv1);
        edt = findViewById(R.id.edtSearch);
        fabAdd = findViewById(R.id.fabAdd);
    }
    protected void onStop() {
        super.onStop();
        dbh.closeDB();
    }
}