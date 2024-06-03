package com.example.btth1_dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtname, edtcmnd, edtttbs;
    CheckBox chkbao, chksach, chkcode;
    Button btnSend;
    RadioGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtcmnd = findViewById(R.id.edtcmnd);
        edtname = findViewById(R.id.edtname);
        edtttbs = findViewById(R.id.edtTTBS);
        chkbao = findViewById(R.id.ckBao);
        chksach = findViewById(R.id.ckSach);
        chkcode = findViewById(R.id.ckCode);
        btnSend = findViewById(R.id.btnsend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doShowInformation();
            }
        });
    }
    public void doShowInformation(){
        String ten =edtname.getText().toString();
        ten = ten.trim();
        if(ten.length()<3){
            edtname.requestFocus();
            edtname.selectAll();
            Toast.makeText(this, "Tên phải trên 3 ký tự", Toast.LENGTH_SHORT).show();
            return;
        }
        String cmnd = edtcmnd.getText().toString();
        cmnd = cmnd.trim();
        if(cmnd.length()!=9){
            edtcmnd.requestFocus();
            edtcmnd.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự",  Toast.LENGTH_SHORT).show();
            return;
        }
        String bang="";
        group = findViewById(R.id.radioGroup);
        int id=group.getCheckedRadioButtonId();// Trả về Id
        if(id==-1)
        {
            Toast.makeText(this, "Phải chọn bằng cấp",
                    Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad= findViewById(id);
        bang=rad.getText()+"";
        String sothich="";
        if(chkbao.isChecked())
            sothich+=chkbao.getText()+"\n";
        if(chksach.isChecked())
            sothich+=chksach.getText()+"\n";
        if(chkcode.isChecked())
            sothich+=chkcode.getText()+"\n";
        String bosung=edtttbs.getText()+"";
        AlertDialog.Builder bder = new AlertDialog.Builder(this);
        bder.setTitle("Thông tin cá nhân");
        bder.setPositiveButton("Đóng", new DialogInterface.OnClickListener(){
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.cancel();
           }
        });
        String msg = ten+"\n";
        msg+=cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich+"\n";
        msg+="Thông tin bổ sung:\n";
        msg+=bosung+ "\n";
        bder.setMessage(msg);
        bder.create().show();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Question");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        showDialog();
    }
}
