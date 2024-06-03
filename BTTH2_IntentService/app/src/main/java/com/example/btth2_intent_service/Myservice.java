package com.example.btth2_intent_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Myservice extends Service {
    MediaPlayer photograph;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        photograph=MediaPlayer.create(Myservice.this, R.raw.photograph);
        photograph.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(photograph == null) {
            photograph = MediaPlayer.create(Myservice.this, R.raw.photograph);
            photograph.setLooping(true);
        }
        if(photograph.isPlaying()) photograph.pause();
        else photograph.start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        photograph.stop();
    }
}
