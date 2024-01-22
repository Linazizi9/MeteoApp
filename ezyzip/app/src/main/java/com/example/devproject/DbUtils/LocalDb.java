package com.example.devproject.DbUtils;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class LocalDb extends RoomDatabase {

public abstract UserDao UserDao();




}
