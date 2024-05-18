package com.example.btth2_intent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    Button btnAtv1, btnTong, btnHieu;
    EditText edt1, edt2;
    Intent int1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAtv1 = findViewById(R.id.atv1);
        btnTong = findViewById(R.id.btnT);
        btnHieu = findViewById(R.id.btnH);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        int1=getIntent();
        int a = int1.getIntExtra("soa",0);
        int b = int1.getIntExtra("sob",0);
        edt1.setText(a+"");
        edt2.setText(b+"");
        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = a+b;
                int1.putExtra("kq", s);
                setResult(33,int1);
                finish();
            }
        });
        btnHieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = a-b;
                int1.putExtra("kq",h);
                setResult(34,int1);
                finish();

            }
        });
        btnAtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(MainActivity2.this, MainActivity2.class);
                startActivity(intent2);
            }
        });
    }
}