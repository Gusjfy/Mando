package com.first.authetication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.first.authetication.BottomNavigationActivity;
import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.MainActivity;
import com.first.authetication.R;
import com.first.authetication.TravelRegister;
import com.first.authetication.model.RecyclerView_Config;
import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private FloatingActionButton floatingButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview_travels);
        floatingButton = view.findViewById(R.id.floatingActionButton);

        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity().getApplicationContext(), TravelRegister.class);
                startActivity(i);
            }
        });


        new DatabaseHelper().listOfTravels(new DatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Travel> travels, List<User> users, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, getActivity().getApplicationContext(), travels, users, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        return view;
    }

}
