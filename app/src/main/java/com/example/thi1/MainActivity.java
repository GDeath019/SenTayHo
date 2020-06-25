package com.example.thi1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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
    int REQUEST = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        dbh = new DBHelper(MainActivity.this);
        dbh.openDB();
        /////
        final ArrayList<HoaDon> ktra = dbh.getAll();
        if(ktra.size()<1){
            add();
        }
        final ArrayList<HoaDon> arrayList = dbh.getAll();
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
                        HoaDon itemRes =(HoaDon) resAdapter.getItem(position);
                        // xóa phần tử theo item nhận được chứ ko dùng postion
                        long x = dbh.Delete(itemRes.getMa());
                        //kiểm tra trong array xem phần tử mà mình xóa là cái nào rồi xóa bỏ nó đi
                        for (int i=0;i<=arrayList.size();i++){
                            if (arrayList.get(i).getMa() == itemRes.getMa()){
                                arrayList.remove(i);
                                break;
                            }
                        }
                        // xóa xong thì update lại adapter để nó biết là dã thay đổi phần tử
                        resAdapter.notifyDataSetChanged();
                        if (edt.getText().length()>0){
                            // filter lại như lúc mình mới xóa để update
                            resAdapter.getFilter().filter(edt.getText());
                        }
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
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AddLocal.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK){
            dbh.openDB();
        }
    }

    private void add() {
        long x =0;
        x += dbh.Insert(1,"30K1-129.84", 12.5, 8800, 5.0);
        x += dbh.Insert(2,"29D2-283.34", 14.3, 8800, 5.0);
        x += dbh.Insert(3,"30K1-129.84", 9.2, 8800, 5.0);
        x += dbh.Insert(4,"29M3-857.65", 9.6, 8800, 5.0);
        x += dbh.Insert(5,"29T2-648.87", 6.5, 8800, 5.0);
        x += dbh.Insert(6,"29T4-746.75", 4.7, 8800, 5.0);
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
//    private void disableEditText() {
//        boolean check = false;
//        edt.setInputType(InputType.TYPE_NULL);
//        edt.setFocusable(check);
//    }
}