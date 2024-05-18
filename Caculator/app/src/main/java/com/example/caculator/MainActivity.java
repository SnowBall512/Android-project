package com.example.caculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    Button btncong, btntru, btntich, btnthuong;
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
        //gọi biến
        edt1  = findViewById(R.id.edta);
        edt2 = findViewById(R.id.edtb);
        edt3 = findViewById(R.id.edtkq);
        btncong = findViewById(R.id.btnTong);
        btntru = findViewById(R.id.btnHieu);
        btntich = findViewById(R.id.btnTich);
        btnthuong = findViewById(R.id.btnThuong);
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int a = Integer.parseInt("0"+ edt1.getText());
               int b = Integer.parseInt("0"+edt2.getText());
               edt3.setText("a + b = " +(a+b));
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                edt3.setText("a - b = "+(a-b));
            }
        });
        btntich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                edt3.setText("a * b = "+(a*b));
            }
        });
        btnthuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0"+edt1.getText());
                int b = Integer.parseInt("0"+edt2.getText());
                if(b==0){
                    edt3.setText("b phải khác 0");
                }
                else {
                    edt3.setText("a / b = " + (a/b));
                }
            }
        });
    }
}