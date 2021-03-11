package com.example.family_tree.DatabaseAccessObjects;

import com.example.family_tree.Models.FamilyMember;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FamilyMemberDao {

    @Insert
    void insert(FamilyMember familyMember);

    @Query("DELETE FROM FamilyMember")
    void deleteAll();

    @Query("SELECT * FROM FamilyMember")
    LiveData<List<FamilyMember>> getAllFamilyMembers();

    @Update
    void update(FamilyMember familyMember);

    @Delete
    void delete(FamilyMember familyMember);
}
