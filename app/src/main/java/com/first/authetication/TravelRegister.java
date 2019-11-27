package com.first.authetication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.first.authetication.DataBaseManager.DatabaseHelper;
import com.first.authetication.model.Travel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TravelRegister extends AppCompatActivity {

    private EditText origem;
    private EditText destino;
    private EditText hora;
    private EditText tfData;
    private EditText tfValor;


    private Button btn;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    Calendar myCalendar = Calendar.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_register);

        origem = findViewById(R.id.Origem);
        destino = findViewById(R.id.destino);
        hora = findViewById(R.id.hora);
        tfData = findViewById(R.id.data);
        tfValor = findViewById(R.id.valor);

        btn = findViewById(R.id.registerTravel);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Travel t = new Travel();
                t.setOrigem(origem.getText().toString());
                t.setDestino(destino.getText().toString());
                t.setHora(hora.getText().toString());
                t.setData(tfData.getText().toString());
                t.setId_entregador(firebaseUser.getUid());
                t.setValor(tfValor.getText().toString());
                new DatabaseHelper().addTravel(t, new DatabaseHelper.DataStatus() {


                    @Override
                    public void DataIsLoaded(List<Travel> travels, List<String> keys) {

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


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        tfData.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                if (tfData.hasFocus()) {
                    new DatePickerDialog(TravelRegister.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                }
            }


        });

        Calendar mcurrentTime = Calendar.getInstance();
        mcurrentTime.get(Calendar.HOUR_OF_DAY);
        mcurrentTime.get(Calendar.MINUTE);

        hora.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {

                if (hora.hasFocus()) {
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(TravelRegister.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            hora.setText(selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();
                }
            }
        });
    }


    private void updateLabel() {

        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));

        tfData.setText(sdf.format(myCalendar.getTime()));
    }
}



