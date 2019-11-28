package com.first.authetication.DataBaseManager;

import android.util.Log;

import androidx.annotation.NonNull;

import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceTravels;
    private DatabaseReference mReferenceUsers;
    private List<Travel> travels = new ArrayList<>();
    private List<Integer> order = new ArrayList<Integer>();
    private int count = -1;

    public DatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceTravels = mDatabase.getReference("travel");
        mReferenceUsers = mDatabase.getReference("users");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Travel> travels, List<String> keys);
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
                    Travel travel = keyNode.getValue(Travel.class);
                    if (!travel.getId_entregador().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        travels.add(travel);
                        order.add(count++);
                        keys.add(keyNode.getKey());
                    }


                }

                dataStatus.DataIsLoaded(travels, ordenaPorData(travels));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void listOfMyTravels(final DataStatus dataStatus){
        mReferenceTravels.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travels.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren()) {
                    Travel travel = keyNode.getValue(Travel.class);
                    if (travel.getId_entregador().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
                        travels.add(travel);
                        order.add(count++);
                        keys.add(keyNode.getKey());
                    }


                }

                dataStatus.DataIsLoaded(travels, ordenaPorData(travels));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addTravel(Travel travel, final DataStatus dataStatus){
        String key = mReferenceTravels.push().getKey();
        travel.setId(key);

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

    private List<String> ordenaPorData(final List<Travel> travels){

        List<String> newList = new ArrayList<>();

        Collections.sort(travels, new Comparator<Travel>() {
            @Override
            public int compare(Travel o1, Travel o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        for (int i = 0; i < travels.size(); i++){
            newList.add(travels.get(i).getId());
        }

       return newList;

    }



}
