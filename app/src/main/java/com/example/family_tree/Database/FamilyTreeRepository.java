package com.example.family_tree.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.family_tree.Database.FamilyTreeRoomDatabase;
import com.example.family_tree.DatabaseAccessObjects.AddressDao;
import com.example.family_tree.DatabaseAccessObjects.ContactInformationDao;
import com.example.family_tree.DatabaseAccessObjects.EmailDao;
import com.example.family_tree.DatabaseAccessObjects.FamilyMemberDao;
import com.example.family_tree.DatabaseAccessObjects.MedicalHistoryNoteDao;
import com.example.family_tree.DatabaseAccessObjects.PhoneNumberDao;
import com.example.family_tree.Models.Address;
import com.example.family_tree.Models.ContactInformation;
import com.example.family_tree.Models.Email;
import com.example.family_tree.Models.FamilyMember;
import com.example.family_tree.Models.MedicalHistoryNote;
import com.example.family_tree.Models.PhoneNumber;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FamilyTreeRepository {

    private FamilyMemberDao mFamilyMemberDao;
    private MedicalHistoryNoteDao mMedicalHistoryNoteDao;
    private ContactInformationDao mContactInformationDao;
    private PhoneNumberDao mPhoneNumberDao;
    private EmailDao mEmailDao;
    private AddressDao mAddressDao;

    private LiveData<List<FamilyMember>> mAllFamilyMembers;
    private LiveData<List<MedicalHistoryNote>> mAllMedicalHistoryNotes;
    private LiveData<List<ContactInformation>> mAllContactInformation;
    private LiveData<List<PhoneNumber>> mAllPhoneNumbers;
    private LiveData<List<Email>> mAllEmails;
    private LiveData<List<Address>> mAllAddresses;

    public FamilyTreeRepository(Application application) {
        FamilyTreeRoomDatabase db = FamilyTreeRoomDatabase.getDatabase(application);
        mFamilyMemberDao = db.familyMemberDao();
        mMedicalHistoryNoteDao = db.medicalHistoryNoteDao();
        mContactInformationDao = db.contactInformationDao();
        mPhoneNumberDao = db.phoneNumberDao();
        mEmailDao = db.emailDao();
        mAddressDao = db.addressDao();

        mAllFamilyMembers = mFamilyMemberDao.getAllFamilyMembers();
        mAllMedicalHistoryNotes = mMedicalHistoryNoteDao.getAllMedicalHistoryNotes();
        mAllContactInformation = mContactInformationDao.getAllContactInformation();
        mAllPhoneNumbers = mPhoneNumberDao.getAllPhoneNumbers();
        mAllEmails = mEmailDao.getAllEmails();
        mAllAddresses = mAddressDao.getAllAddresses();
    }

    public LiveData<List<FamilyMember>> getAllFamilyMembers() {
        return mAllFamilyMembers;
    }

    public LiveData<List<MedicalHistoryNote>> getAllMedicalHistoryNotes() {
        return mAllMedicalHistoryNotes;
    }

    public LiveData<List<ContactInformation>> getAllContactInformation() {
        return mAllContactInformation;
    }

    public LiveData<List<PhoneNumber>> getAllPhoneNumbers() {
        return mAllPhoneNumbers;
    }

    public LiveData<List<Email>> getAllEmails() {
        return mAllEmails;
    }

    public LiveData<List<Address>> getAllAddresses() {
        return mAllAddresses;
    }

    public void insertFamilyMember(FamilyMember familyMember) {
        new insertFamilyMemberAsyncTask(mFamilyMemberDao).execute(familyMember);
    }

    private static class insertFamilyMemberAsyncTask extends AsyncTask<FamilyMember, Void, Void> {
        private FamilyMemberDao mAsyncTaskDao;

        insertFamilyMemberAsyncTask(FamilyMemberDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FamilyMember... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertMedicalHistoryNoteAsyncTask extends AsyncTask<MedicalHistoryNote, Void, Void> {
        private MedicalHistoryNoteDao mAsyncTaskDao;

        insertMedicalHistoryNoteAsyncTask(MedicalHistoryNoteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MedicalHistoryNote... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertContactInformationAsyncTask extends AsyncTask<ContactInformation, Void, Void> {
        private ContactInformationDao mAsyncTaskDao;

        insertContactInformationAsyncTask(ContactInformationDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ContactInformation... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertPhoneNumberTask extends AsyncTask<PhoneNumber, Void, Void> {
        private PhoneNumberDao mAsyncTaskDao;

        insertPhoneNumberTask(PhoneNumberDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PhoneNumber... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertEmailAsyncTask extends AsyncTask<Email, Void, Void> {
        private EmailDao mAsyncTaskDao;

        insertEmailAsyncTask(EmailDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Email... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAddressAsyncTask extends AsyncTask<Address, Void, Void> {
        private AddressDao mAsyncTaskDao;

        insertAddressAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Address... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}