package com.example.family_tree.DatabaseAccessObjects;

import com.example.family_tree.Models.PhoneNumber;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PhoneNumberDao {

    @Insert
    void insert(PhoneNumber phoneNumber);

    @Query("DELETE FROM PhoneNumber")
    void deleteAll();

    @Query("SELECT * FROM PhoneNumber")
    LiveData<List<PhoneNumber>> getAllPhoneNumbers();
}
