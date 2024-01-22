package com.example.devproject.DbUtils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
     @Insert
    void creatUser(User user);
     @Query("SELECT * FROM user WHERE mail like:strmail")
    User getUserByMail(String strmail);


}
