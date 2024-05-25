package com.example.btth2_intent1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    EditText edt1, edt2;

    @SuppressLint("MissingInflatedId")
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
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btn1 = findViewById(R.id.btnKQ);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input1 = edt1.getText().toString();
                String input2 = edt2.getText().toString();

                if (input1.isEmpty() || input2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter numbers in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int a = Integer.parseInt(input1);
                    int b = Integer.parseInt(input2);

                    Intent intent1 = new Intent(MainActivity.this, child_activity1.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("soa", a);
                    bundle1.putInt("sob", b);
                    intent1.putExtra("mybackage", bundle1);
                    startActivity(intent1);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
