package com.example.family_tree.Database;

import android.content.Context;

import com.example.family_tree.DatabaseAccessObjects.FamilyMemberDao;
import com.example.family_tree.Models.FamilyMember;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FamilyMember.class}, version = 1, exportSchema = false)
public abstract class FamilyMemberRoomDatabase extends RoomDatabase {

    public abstract FamilyMemberDao familyMemberDao();
    private static FamilyMemberRoomDatabase INSTANCE;

    static FamilyMemberRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FamilyMemberRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), FamilyMemberRoomDatabase.class, "FamilyTreeDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
