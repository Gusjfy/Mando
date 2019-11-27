package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    TextView txtNome, txtOrigem, txtDestino, txtValor, txtData, txtHora;


    FirebaseUser firebaseUser;
    //    DatabaseReference referenceUser;
    DatabaseReference reference;
    DatabaseReference referenceUser;
    Travel travel;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_info);

        txtNome = findViewById(R.id.txtNomeEntregador);
        txtOrigem = findViewById(R.id.txtOrigem);
        txtDestino = findViewById(R.id.txtDestino);
        txtValor = findViewById(R.id.txtValor);
        txtData = findViewById(R.id.txtData);
        txtHora = findViewById(R.id.txtHora);



        intent = getIntent();
        String cod = intent.getStringExtra("travelid");
        reference = FirebaseDatabase.getInstance().getReference("travel").child(cod);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travel = dataSnapshot.getValue(Travel.class);
                txtOrigem.setText(travel.getOrigem());
                txtDestino.setText(travel.getDestino());
                txtValor.setText("R$" + travel.getValor());
                txtData.setText(travel.getData());
                txtHora.setText(travel.getHora());
                referenceUser = FirebaseDatabase.getInstance().getReference("users").child(travel.getId_entregador());
                referenceUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                        txtNome.setText(user.getNome());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


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
