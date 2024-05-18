package com.example.btth01;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener {

    private  EditText billedt;
    private TextView percenttv;
    private Button btndown, btnup;
    private TextView tiptv;
    private TextView totaltv;

    private SharedPreferences savedValues;

    private String billAmountStr = "";
    private float tipPercent = .15f;

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
        billedt = (EditText) findViewById(R.id.billAmountEdt);
        percenttv = (TextView) findViewById(R.id.percentTv);
        btnup = (Button) findViewById(R.id.percentbtnUp);
        btndown = (Button) findViewById(R.id.percentbtnDown);
        tiptv = (TextView) findViewById(R.id.tipTv);
        totaltv =(TextView) findViewById(R.id.totalTv);

        billedt.setOnEditorActionListener(this);
        btnup.setOnClickListener(this);
        btndown.setOnClickListener(this);

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("billAmountStr", billAmountStr);
        editor.putFloat("tip", tipPercent);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        // get the instance variables
        billAmountStr = savedValues.getString("billAmountStr", "");
        tipPercent = savedValues.getFloat("tip", 0.15f);

        // set the bill amount on its widget
        billedt.setText(billAmountStr);

        // calculate and display
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {

        // get the bill amount
        billAmountStr = billedt.getText().toString();
        float billAmount;
        if (billAmountStr.equals("")) {
            billAmount = 0;
        } else {
            billAmount = Float.parseFloat(billAmountStr);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tiptv.setText(currency.format(tipAmount));
        totaltv.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMinimumFractionDigits(1);
        percenttv.setText(percent.format(tipPercent));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE ||
                actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.percentbtnDown) {
            tipPercent = tipPercent - .01f;
            calculateAndDisplay();
        } else if (id == R.id.percentbtnUp) {
            tipPercent = tipPercent + .01f;
            calculateAndDisplay();
        }
    }
}
