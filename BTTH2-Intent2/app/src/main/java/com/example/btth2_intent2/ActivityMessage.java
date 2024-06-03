package com.example.btth2_intent2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityMessage extends AppCompatActivity {
    EditText edtmess;
    ImageButton btnmess;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);

        edtmess = findViewById(R.id.edtmess); // Ensure this ID matches your XML
        btnmess = findViewById(R.id.btnmess);
        btnback = findViewById(R.id.btnbackmess);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent im = new Intent(ActivityMessage.this, MainActivity.class);
                finish();
                startActivity(im);
            }
        });
        btnmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent im = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+edtmess.getText().toString()));
                startActivity(im);
            }
        });
    }
}
