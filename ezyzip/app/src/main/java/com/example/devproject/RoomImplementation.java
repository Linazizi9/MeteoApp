package com.example.devproject;

import android.app.Application;

import androidx.room.Room;

import com.example.devproject.DbUtils.LocalDb;

public class RoomImplementation extends Application {

    private static RoomImplementation mInstance;
    private LocalDb dbInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        dbInstance= Room.databaseBuilder(getApplicationContext(),LocalDb.class,"LocalDb").build();
    }
    public static RoomImplementation getmInstance(){
        return mInstance;
    }
    public LocalDb getDbInstance(){
        return dbInstance;
    }
}
