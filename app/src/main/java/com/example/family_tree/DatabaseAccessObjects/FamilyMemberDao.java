package com.example.family_tree.DatabaseAccessObjects;

import com.example.family_tree.Models.FamilyMember;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface FamilyMemberDao {

    @Insert
    void insert(FamilyMember familyMember);

    @Query("DELETE FROM FamilyMember")
    void deleteAll();

    @Query("SELECT * FROM FamilyMember")
    LiveData<List<FamilyMember>> getAllFamilyMembers();

}
