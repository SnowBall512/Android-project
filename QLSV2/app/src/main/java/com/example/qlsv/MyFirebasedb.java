package com.example.qlsv;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MyFirebasedb extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
