package com.first.authetication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class TravelRegister extends AppCompatActivity {

    private EditText origem;
    private EditText destino;
    private EditText hora;
    private EditText data;

    private Button btn;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_register);

        origem = findViewById(R.id.Origem);
        destino = findViewById(R.id.destino);
        hora = findViewById(R.id.hora);
        data = findViewById(R.id.data);

        btn = findViewById(R.id.registerTravel);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Travel t = new Travel();
                t.setOrigem(origem.getText().toString());
                t.setDestino(destino.getText().toString());
                t.setHora(hora.getText().toString());
                t.setData(data.getText().toString());
                t.setId_entregador(firebaseUser.getUid());
                new DatabaseHelper().addTravel(t, new DatabaseHelper.DataStatus() {


                    @Override
                    public void DataIsLoaded(List<Travel> travels, List<User> users, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(TravelRegister.this, "A viagem foi cadastrada com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                Intent i = new Intent(TravelRegister.this, BottomNavigationActivity.class);
                startActivity(i);
            }
        });

    }
}
