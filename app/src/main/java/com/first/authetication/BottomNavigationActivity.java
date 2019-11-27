package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.UI.ChatFragment;
import com.first.authetication.UI.HomeFragment;
//import com.first.authetication.UI.PerfilFragment;
import com.first.authetication.UI.PerfilFragment;
import com.first.authetication.UI.SearchFragment;
import com.first.authetication.model.RecyclerView_Config;
import com.first.authetication.model.Travel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        mRecyclerView = findViewById(R.id.recyclerview_travels);

        Toolbar actionBarToolBar = findViewById(R.id.myToolBbar);
        setSupportActionBar(actionBarToolBar);


    }

    @Override
    public void onBackPressed() {

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;

//                case R.id.navigation_busca:
//                    selectedFragment = new SearchFragment();
//                    break;

                case R.id.navigation_chat:
                    selectedFragment = new ChatFragment();
                    break;

                case R.id.navigation_perfil:
                    selectedFragment = new PerfilFragment();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

}
