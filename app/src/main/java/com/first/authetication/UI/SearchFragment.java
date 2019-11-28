package com.first.authetication.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.R;
import com.first.authetication.model.MyRecyclerView_Config;
import com.first.authetication.model.RecyclerView_Config;
import com.first.authetication.model.Travel;

import java.util.List;

public class SearchFragment extends Fragment {


    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        mRecyclerView = view.findViewById(R.id.recyclerview_travels);

        new DatabaseHelper().listOfMyTravels(new DatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Travel> travels, List<String> keys) {
                new MyRecyclerView_Config().setConfig(mRecyclerView, getActivity(), travels, keys);
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
