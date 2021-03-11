package com.example.family_tree.DatabaseAccessObjects;

import com.example.family_tree.Models.Email;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface EmailDao {

    @Insert
    void insert(Email email);

    @Query("DELETE FROM Email")
    void deleteAll();

    @Query("SELECT * FROM Email")
    LiveData<List<Email>> getAllEmails();
}
