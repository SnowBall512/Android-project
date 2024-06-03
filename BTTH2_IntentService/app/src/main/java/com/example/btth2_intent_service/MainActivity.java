package com.example.btth2_intent_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView img_music;
    ImageButton btnplay, btnpause;
    Boolean flag=true;
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
        btnplay=findViewById(R.id.btnplay);
        btnpause=findViewById(R.id.btnpause);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iplay = new Intent(MainActivity.this, Myservice.class);
                startService(iplay);
                if (flag ==true){
                    btnplay.setImageResource(R.drawable.img_pause);
                    flag=false;
                }
                else {
                    btnplay.setImageResource(R.drawable.img_play);
                    flag=true;
                }
            }
        });
        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ipause = new Intent(MainActivity.this, Myservice.class);
                stopService(ipause);
                btnplay.setImageResource(R.drawable.img_play);
                flag=true;
            }
        });
    }
}