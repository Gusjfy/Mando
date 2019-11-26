package com.first.authetication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class MessageActivity extends AppCompatActivity {

    private TextView username;

    private FirebaseUser fuser;
    private DatabaseReference reference;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        username = findViewById(R.id.username);
        //Return the intent that started this activity.
        intent = getIntent();
        String userId = intent.getStringExtra("userid");

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        fuser = FirebaseAuth.getInstance().getCurrentUser();fuser = FirebaseAuth.getInstance().getCurrentUser();
        fuser = FirebaseAuth.getInstance().getCurrentUser();fuser = FirebaseAuth.getInstance().getCurrentUser();
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        fuser = FirebaseAuth.getInstance().getCurrentUser();fuser = FirebaseAuth.getInstance().getCurrentUser();fuser = FirebaseAuth.getInstance().getCurrentUser();




    }
}
