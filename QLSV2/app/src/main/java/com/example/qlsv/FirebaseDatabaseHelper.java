package com.example.qlsv;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefStudent;
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefStudent = mDatabase.getReference("student");
    }
    public void addStudent(Student st){
        String id=mRefStudent.push().getKey();
        st.setId(id);
        mRefStudent.child(id).setValue(st);
    }
    public void updateStudent(String id,Student st){
        mRefStudent.child(id).setValue(st);
    }
    public void deleteStudent(String id){
        mRefStudent.child(id).removeValue();
    }
    public DatabaseReference getReference(){
        return mRefStudent;
    }
}
