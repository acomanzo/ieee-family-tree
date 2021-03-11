package com.example.family_tree.ViewModels;

import android.app.Application;
import android.util.Log;

import com.example.family_tree.Database.FamilyTreeRepository;
import com.example.family_tree.Models.FamilyMember;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FamilyMemberViewModel extends AndroidViewModel {

    private FamilyTreeRepository mRepository;

    private LiveData<List<FamilyMember>> mAllFamilyMembers;

    public FamilyMemberViewModel (Application application) {
        super(application);
        mRepository = new FamilyTreeRepository(application);
        mAllFamilyMembers = mRepository.getAllFamilyMembers();
        Log.i("FamilyMemberViewModel", "Created FamilyMemberViewModel");
    }

    public LiveData<List<FamilyMember>> getAllFamilyMembers() {
        return mAllFamilyMembers;
    }

    public void insert(FamilyMember familyMember) {
        mRepository.insertFamilyMember(familyMember);
    }

    public void update(FamilyMember familyMember) {
        mRepository.updateFamilyMember(familyMember);
    }

    public void delete(FamilyMember familyMember) {
        mRepository.deleteFamilyMember(familyMember);
    }

    public FamilyMember getFamilyMemberAtIndex(int position) {
        return mAllFamilyMembers.getValue().get(position);
    }
}
