package com.example.responsi;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.responsi.room.AppDatabase;

public class AppApplication extends Application {
    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "com/example/finalprojectjava/mahasiswa").allowMainThreadQueries().build();
    }
}
