package com.first.authetication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.first.authetication.model.Travel;
import com.first.authetication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditarTravelActivity extends AppCompatActivity {

    Intent intent;

    DatabaseReference reference;


    private EditText origem;
    private EditText destino;
    private EditText hora;
    private EditText tfData;
    private EditText tfValor;
    private Button btn;

    private Travel travel;

    Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_travel);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intent = getIntent();

        String id = intent.getStringExtra("travelid");


        origem = findViewById(R.id.Origem);
        destino = findViewById(R.id.destino);
        hora = findViewById(R.id.hora);
        tfData = findViewById(R.id.data);
        tfValor = findViewById(R.id.valor);
        btn = findViewById(R.id.registerTravel);

        reference = FirebaseDatabase.getInstance().getReference("travel").child(id);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travel = dataSnapshot.getValue(Travel.class);

                origem.setText(travel.getOrigem());
                destino.setText(travel.getDestino());
                hora.setText(travel.getHora());
                tfData.setText(travel.getData());
                tfValor.setText(travel.getValor());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                travel.setOrigem(origem.getText().toString());
                travel.setDestino(destino.getText().toString());
                travel.setHora(hora.getText().toString());
                travel.setData(tfData.getText().toString());
                travel.setValor(tfValor.getText().toString());
                reference.setValue(travel);
                finish();
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
                    new DatePickerDialog(EditarTravelActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
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
                    mTimePicker = new TimePickerDialog(EditarTravelActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
