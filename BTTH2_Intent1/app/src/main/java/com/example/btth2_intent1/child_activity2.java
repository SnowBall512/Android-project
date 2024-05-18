package com.example.btth2_intent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/** @noinspection ALL*/
public class child_activity2 extends AppCompatActivity {

    Button btnAtv1, btnKQ;
    EditText edtA, edtB, edtKQ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_child2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAtv1 = findViewById(R.id.atv1);
        btnKQ = findViewById(R.id.btnKQ);
        edtA = findViewById(R.id.edt1);
        edtB = findViewById(R.id.edt2);
        edtKQ = findViewById(R.id.edt3);
        btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(child_activity2.this, MainActivity2.class);
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                intent1.putExtra("soa",a);
                intent1.putExtra("sob",b);
                startActivityForResult(intent1,99);
            }
        });
        btnAtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(child_activity2.this, MainActivity2.class);
                startActivity(intent2);
            }
        });
    }
    @Override
    protected void onActivityResult (int request, int result, @Nullable Intent data){
        super.onActivityResult(request, result, data);
        if(request == 99 && result==33){
            int s = data.getIntExtra("kq",0);
            edtKQ.setText("Tổng hai số là: " + s);
        }
        if(request == 99 && result==34){
            int h = data.getIntExtra("kq",0);
            edtKQ.setText("Hiệu hai số là: " + h);
        }
    }
}