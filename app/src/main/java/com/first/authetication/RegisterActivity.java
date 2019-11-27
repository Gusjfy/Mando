package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button registrar;
    FirebaseAuth mAuth;
    EditText name;
    EditText tel;
    EditText cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.edtNome);
        email = (EditText) findViewById(R.id.edtEmail); //id pode estar diferente
        password = (EditText) findViewById(R.id.edtPassword); //id pode estar diferente
        tel = findViewById(R.id.edtNumero);
        cpf = findViewById(R.id.edtCPF);
        registrar = findViewById(R.id.btnRegister); //id pode estar diferente



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUp(email.getText().toString(), password.getText().toString());

            }

        });
    }

    private void singUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in User's information
                            Log.d("TA", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            registerUserDataBase(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the User.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void registerUserDataBase(FirebaseUser user){
        User usuario = new User();
        usuario.setEmail(email.getText().toString());
        usuario.setNome(name.getText().toString());
        usuario.setSenha(password.getText().toString());
        usuario.setTelefone(tel.getText().toString());
        usuario.setCpf(cpf.getText().toString());
        usuario.setId(user.getUid());

        new DatabaseHelper().addUser(user.getUid() ,usuario, new DatabaseHelper.DataStatus() {


            @Override
            public void DataIsLoaded(List<Travel> travels, List<String> keys) {

            }

            @Override
            public void DataIsInserted() {
                Toast.makeText(RegisterActivity.this, "O cadastro foi efetuado com sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }






    private void updateUI(FirebaseUser user) {
        Intent i = new Intent(RegisterActivity.this, BottomNavigationActivity.class);
        startActivity(i);
    }
}
