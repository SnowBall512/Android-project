package com.example.btth01_control1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edtdoC, edtdoF;
    Button btncf, btnfc, btnClear;
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
        edtdoC = findViewById(R.id.etC);
        edtdoF = findViewById(R.id.etF);
        btncf = findViewById(R.id.btnC);
        btnfc = findViewById(R.id.btnF);
        btnClear= findViewById(R.id.btnClear);
        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doC = edtdoC.getText()+"";
                //
                int C = Integer.parseInt(doC);
                edtdoF.setText(""+dcf.format(C*1.8+32));
//                String input = edtdoC.getText().toString();
//                if (input.isEmpty()) {
//                    // Nếu không có đầu vào, hiển thị thông báo yêu cầu nhập giá trị
//                    showAlertDialog("Vui lòng nhập giá trị vào ô độ C.");
//                    return;
//                }
//
//                // Kiểm tra xem đầu vào có phải là số không
//                if (!isNumeric(input)) {
//                    // Nếu không phải là số, hiển thị thông báo yêu cầu nhập số
//                    showAlertDialog("Vui lòng nhập số vào ô độ C.");
//                    return;
//                }
//
//                // Tiếp tục chuyển đổi nếu đầu vào hợp lệ
//                DecimalFormat dcf = new DecimalFormat("#.00");
//                double C = Double.parseDouble(input);
//                edtdoF.setText(dcf.format(C * 1.8 + 32));

            }
        });
        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DecimalFormat dcf = new DecimalFormat("#.00");//định dạng số thành một chuỗi dựa trên một mẫu cụ thể.
                //Dấu thập phân và các chữ số phần thập phân được xác định bởi "#".(phần trc dấu ,)
                //Hai chữ số sau dấu thập phân đại diện cho số chữ số thập phân sẽ được hiển thị. Nếu có ít hơn hai chữ số thập phân, các chữ số 0 sẽ được thêm vào cuối.
                String doF = edtdoF.getText()+"";
                //
                int F = Integer.parseInt(doF);
                edtdoC.setText(""+dcf.format((F-32)/1.8));
                //đặt văn bản trong edtdoC thành kết quả của phép chuyển đổi từ độ Fahrenheit sang Celsius, đã được định dạng theo mẫu "#.00".
                //dcf.format: định dạng kết quả của phép tính


//                String input = edtdoF.getText().toString();
//                if (input.isEmpty()) {
//                    // Nếu không có đầu vào, hiển thị thông báo yêu cầu nhập giá trị
//                    showAlertDialog("Vui lòng nhập giá trị vào ô độ F.");
//                    return;
//                }
//
//                // Kiểm tra xem đầu vào có phải là số không
//                if (!isNumeric(input)) {
//                    // Nếu không phải là số, hiển thị thông báo yêu cầu nhập số
//                    showAlertDialog("Vui lòng nhập số vào ô độ F.");
//                    return;
//                }
//
//                // Tiếp tục chuyển đổi nếu đầu vào hợp lệ
//                DecimalFormat dcf = new DecimalFormat("#.00");
//                double F = Double.parseDouble(input);
//                edtdoC.setText(dcf.format((F - 32) / 1.8));
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtdoC.getText().clear();
                edtdoF.getText().clear();
            }
        });

    }
//    private boolean isNumeric(String str) {
//        return str.matches("-?\\d+(\\.\\d+)?");
//    }
//
//    // Phương thức hiển thị AlertDialog với thông báo cụ thể
//    private void showAlertDialog(String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setMessage(message)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User clicked OK button, do nothing
//                    }
//                });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
}