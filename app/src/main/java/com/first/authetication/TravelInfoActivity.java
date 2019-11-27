package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TravelInfoActivity extends AppCompatActivity {

    private Context mContext = TravelInfoActivity.this;
    private Intent intent;

    private Button btnChat;

    FirebaseUser firebaseUser;
    //    DatabaseReference referenceUser;
    DatabaseReference reference;
    Travel travel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        intent = getIntent();
        String cod = intent.getStringExtra("travelid");

        Log.d("vsf", cod);

        reference = FirebaseDatabase.getInstance().getReference("travel").child(cod);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travel = dataSnapshot.getValue(Travel.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnChat = findViewById(R.id.buttonEntrarEmContato);

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("vsf", travel.getId_entregador());
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", travel.getId_entregador());
                mContext.startActivity(intent);
            }
        });


    }
}
