package com.example.btth;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtMa, edtTen, edtSo;
    Button btnIn, btnDel, btnUp, btnQue;
    ListView list;
    ArrayList<String> myList;
    ArrayAdapter<String> myAdapter;
    SQLiteDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtMa=findViewById(R.id.edtMa);
        edtTen=findViewById(R.id.edtTen);
        edtSo=findViewById(R.id.edtSo);
        btnDel = findViewById(R.id.btnDel);
        btnIn = findViewById(R.id.btnInsert);
        btnQue=findViewById(R.id.btnQuery);
        btnUp=findViewById(R.id.btnUpdate);
        list= findViewById(R.id.list);
        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,myList);;
        list.setAdapter(myAdapter);
        mydb = openOrCreateDatabase("qlsv.db",MODE_PRIVATE, null);
        try {
            String sql="Create table tblop(malop TEXT primary key, tenlop TEXT,siso INTERGER)";
            mydb.execSQL(sql);
        }
        catch (Exception e){
            Log.e("Error","Table đã tồn tại");
        }
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop =edtMa.getText().toString();
                String tenLop=edtTen.getText().toString();
                int siso = Integer.parseInt(edtSo.getText().toString());
                ContentValues myVl=new ContentValues();
                myVl.put("malop",maLop);
                myVl.put("tenlop",tenLop);
                myVl.put("siso",siso);
                String msg="";
                if(mydb.insert("tblop", null, myVl)==1){
                    msg="Fail ";
                }
                else {
                    msg="Success";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
        btnQue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myList.clear();;
                Cursor c=mydb.query("tblop", null, null, null,null, null,null);
                c.moveToNext();
                String data="";
                while(c.isAfterLast()==false){
                    data=c.getString(0)+" - "+c.getString(1)+" -"+c.getString(2);
                    c.moveToNext();
                    myList.add(data);

                }
                c.close();
                myAdapter.notifyDataSetChanged();
            }
        });
        btnDel


    }
}