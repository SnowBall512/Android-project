package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button btnIn, btnDel, btnUp;
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
        edtMa = findViewById(R.id.edtMa);
        edtTen = findViewById(R.id.edtTen);
        edtSo = findViewById(R.id.edtSo);
        btnDel = findViewById(R.id.btnDel);
        btnIn = findViewById(R.id.btnInsert);
        btnUp = findViewById(R.id.btnUpdate);
        list = findViewById(R.id.list);
        myList = new ArrayList<>();
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        list.setAdapter(myAdapter);
        mydb = openOrCreateDatabase("qlsv.db", MODE_PRIVATE, null);
        try {
            String sql = "Create table tblop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            mydb.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table đã tồn tại");
        }

//        edtMa.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    String maLop = edtMa.getText().toString();
//                    if (!maLop.isEmpty()) {
//                        displayClassInfo(maLop);
//                        refreshList();
//                    }
//                }
//            }
//        });
// Đăng ký TextWatcher cho EditText mã lớp
        edtMa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String maLop = s.toString();
                displayClassInfo(maLop);
                refreshList();
            }
        });
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMa.getText().toString();
                String tenLop = edtTen.getText().toString();
                String sisoStr = edtSo.getText().toString();

                if (!isNumeric(sisoStr)) {
                    Toast.makeText(MainActivity.this, "Sĩ số phải là một số", Toast.LENGTH_SHORT).show();
                    return;
                }

                int siso = Integer.parseInt(sisoStr);

                if (checkMalopExists(maLop)) {
                    Toast.makeText(MainActivity.this, "Mã lớp đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues myVl = new ContentValues();
                    myVl.put("malop", maLop);
                    myVl.put("tenlop", tenLop);
                    myVl.put("siso", siso);

                    long result = mydb.insert("tblop", null, myVl);
                    if (result == -1) {
                        Toast.makeText(MainActivity.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        refreshList(); // Refresh the list view
                    }
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maLop = edtMa.getText().toString();
                int n = mydb.delete("tblop", "malop = ?", new String[]{maLop});
                String msg = "";
                if (n == 0) {
                    msg = "Không có gì để xóa";
                } else {
                    msg = n + " Xóa thành công";
                    refreshList(); // Refresh the list view
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sisoStr = edtSo.getText().toString();

                if (!isNumeric(sisoStr)) {
                    Toast.makeText(MainActivity.this, "Sĩ số phải là một số", Toast.LENGTH_SHORT).show();
                    return;
                }

                int siso = Integer.parseInt(sisoStr);
                String tenLop = edtTen.getText().toString();
                String malop = edtMa.getText().toString();
                ContentValues myVl = new ContentValues();
                myVl.put("siso", siso);
                myVl.put("tenlop", tenLop);
                int n = mydb.update("tblop", myVl, "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "Cập nhật không thành công";
                } else {
                    msg = n + " Cập nhật thành công";
                    refreshList(); // Refresh the list view
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        refreshList();
    }

    private void refreshList() {
        myList.clear();
        String malop = edtMa.getText().toString().trim();
        Cursor c;
        if (!malop.isEmpty()) {
            c = mydb.rawQuery("SELECT * FROM tblop WHERE malop LIKE ?", new String[]{"%" + malop + "%"});
        } else {
            c = mydb.query("tblop", null, null, null, null, null, null);
        }
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
            myList.add(data);
            c.moveToNext();
        }
        c.close();
        myAdapter.notifyDataSetChanged();
    }


    private void displayClassInfo(String malop) {
        Cursor cursor = mydb.rawQuery("SELECT * FROM tblop WHERE malop=?", new String[]{malop});
        if (cursor.moveToFirst()) {
            String tenLop = cursor.getString(cursor.getColumnIndexOrThrow("tenlop"));
            int siso = cursor.getInt(cursor.getColumnIndexOrThrow("siso"));
            edtTen.setText(tenLop);
            edtSo.setText(String.valueOf(siso));
        } else {
            edtTen.setText("");
            edtSo.setText("");
        }
        cursor.close();
    }

    private boolean checkMalopExists(String malop) {
        Cursor cursor = mydb.rawQuery("SELECT * FROM tblop WHERE malop=?", new String[]{malop});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
