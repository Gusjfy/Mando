package com.first.authetication.DataBaseManager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceTravels;
    private DatabaseReference mReferenceUsers;
    private List<Travel> travels = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public DatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceTravels = mDatabase.getReference("travel");
        mReferenceUsers = mDatabase.getReference("users");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Travel> travels,List<User> users, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void listOfTravels(final DataStatus dataStatus){
        mReferenceTravels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travels.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Travel travel = keyNode.getValue(Travel.class);
                    travels.add(travel);

                }
                listOfUsers(dataStatus, travels, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void listOfUsers(final DataStatus dataStatus, final List<Travel> travels, final List<String> keys){
        mReferenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()) {
                    User user = keyNode.getValue(User.class);
                    users.add(user);
                }
                dataStatus.DataIsLoaded(travels, users, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addTravel(Travel travel, final DataStatus dataStatus){
        String key = mReferenceTravels.push().getKey();
        mReferenceTravels.child(key).setValue(travel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }

    public void addUser(String key ,User usuario, final DataStatus dataStatus){
        mReferenceUsers.child(key).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }


}
