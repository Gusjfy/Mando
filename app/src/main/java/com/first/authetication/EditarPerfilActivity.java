package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.first.authetication.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditarPerfilActivity extends AppCompatActivity {

    EditText edtNome, edtBio, edtFone;
    Button btnSalvar;

    FirebaseUser firebaseUser;

    DatabaseReference reference;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        edtNome = findViewById(R.id.editTextNome);
        edtBio = findViewById(R.id.editTextBio);
        edtFone = findViewById(R.id.editTextFone);
        btnSalvar = findViewById(R.id.btnSalvarPerfil);



        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                edtNome.setText(user.getNome());
                edtBio.setText(user.getBio());
                edtFone.setText(user.getTelefone());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    btnSalvar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            user.setNome(edtNome.getText().toString());
            user.setTelefone(edtFone.getText().toString());
            user.setBio(edtBio.getText().toString());
            reference.setValue(user);
            finish();
        }
    });










    }
}
