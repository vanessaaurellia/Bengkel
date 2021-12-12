package com.example.bengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class Form extends AppCompatActivity {

    Spinner spinnerJenis, spinnerTipe;
    Button btn_lihat;

    ArrayList<String> arrayJenis, arrayRodaDua, arrayRodaEmpat;
    ArrayAdapter<String> tipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spinnerJenis = findViewById(R.id.spinnerJenis);
        spinnerTipe = findViewById(R.id.spinnerTipe);
        btn_lihat = findViewById(R.id.btn_lihat);

        arrayJenis = new ArrayList<>();
        arrayJenis.add("Roda Dua");
        arrayJenis.add("Roda Empat");

        arrayRodaDua = new ArrayList<>();
        arrayRodaDua.add("Sport Bike");
        arrayRodaDua.add("Moped");

        arrayRodaEmpat = new ArrayList<>();
        arrayRodaEmpat.add("SUV");
        arrayRodaEmpat.add("Sedan");

        ArrayAdapter<String> jenisAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayJenis);
        spinnerJenis.setAdapter(jenisAdapter);

        spinnerJenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                //untuk menentukan spinner ke 2 jika spinner ke 1 di click
                if(position == 0){
                    tipeAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayRodaDua);
                }

                if(position == 1){
                    tipeAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayRodaEmpat);
                }
                spinnerTipe.setAdapter(tipeAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
            }
        });

        btn_lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPanduan();
            }
        });

    }

    //pindah screen
    public void openPanduan() {
        String pilihJenis = spinnerJenis.getSelectedItem().toString();
        String pilihTipe = spinnerTipe.getSelectedItem().toString();

        //untuk passing data
        Intent intent = new Intent(this, Panduan.class);
        intent.putExtra("currentJenis", pilihJenis);
        intent.putExtra("currentTipe", pilihTipe);
        startActivity(intent);
    }

}
