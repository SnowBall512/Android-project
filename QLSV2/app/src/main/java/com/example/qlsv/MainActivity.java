package com.example.qlsv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtName, edtEmail;
    private Button btnAdd, btnUp, btnDel;
    private ListView lsvStudent ;
    private List<Student> studentList;
    private FirebaseDatabaseHelper dbHelper;
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
    edtName = findViewById(R.id.edtName);
    edtEmail = findViewById(R.id.edtEmail);
    btnAdd = findViewById(R.id.btnAdd);
    btnUp = findViewById(R.id.btnUpdate);
    btnDel = findViewById(R.id.btnDel);
    lsvStudent = findViewById(R.id.lsvStudents);
    studentList = new ArrayList<>();
    dbHelper = new FirebaseDatabaseHelper();
    btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addStudent();
        }
    });
    btnUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            updateStudent();
        }   
    });

    btnDel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            deleteStudent();
        }
    });
    loadStudent();

    }

    private void loadStudent() {

    }

    private void deleteStudent() {
        String id = getSeleltedStudent();
        dbHelper.deleteStudent(id);
    }

    private void updateStudent() {
        String id = getSeleltedStudent();
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        Student student = new Student(id,name, email);
        dbHelper.updateStudent(id,student);
    }

    private void addStudent() {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String id = dbHelper.getReference().push().getKey();
        Student student = new Student(id,name, email);
        dbHelper.addStudent(student);
    }

    public String getSeleltedStudent() {
        int selectedPosition = lsvStudent.getCheckedItemPosition(); // or recyclerView.getSelectedPosition()
        if (selectedPosition != ListView.INVALID_POSITION) {
            Student selectedStudent = (Student) lsvStudent.getItemAtPosition(selectedPosition);
            return selectedStudent.getId();
        }
        return null; // or handle appropriately if no student
    }
}