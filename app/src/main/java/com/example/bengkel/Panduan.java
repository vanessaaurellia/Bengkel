package com.example.bengkel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Panduan extends AppCompatActivity {

    TextView textJenis, textTipe, textEngine, textGear;
    String previewEngine = "";
    String previewGear = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan);

        textJenis = findViewById(R.id.textJenis);
        textTipe = findViewById(R.id.textTipe);
        textEngine = findViewById(R.id.textEngine);
        textGear = findViewById(R.id.textGear);

        //memanggil class menjadi object
        getValue value = new getValue();

        textJenis.setText(value.jenis);
        textTipe.setText(value.tipe);

        engine engine = new engine();
        if(value.jenis.equals("Roda Dua")){
            engine.rodaDua();
        }
        else if(value.jenis.equals("Roda Empat")){
            engine.rodaEmpat();
        }

        gear gear = new gear();
        if(value.tipe.equals("Sport Bike")){
            gear.sportBike();
        }
        else if(value.tipe.equals("Moped")){
            gear.moped();
        }
        else if(value.tipe.equals("SUV")){
            gear.suv();
        }
        else if(value.tipe.equals("Sedan")){
            gear.sedan();
        }
    }

    public class getValue {
        Intent intent = getIntent();
        String jenis = intent.getStringExtra("currentJenis");
        String tipe = intent.getStringExtra("currentTipe");
    }

    public class engine{
        void rodaDua(){
            previewEngine = "Buka penutup mesin. Cek kondisi mesin. Ganti oli mesin.";
            textEngine.setText(previewEngine);
        }

        void rodaEmpat(){
            previewEngine = "Buka kompartemen mesin. Cek kondisi mesin. Ganti oli mesin. Cek pendingin.";
            textEngine.setText(previewEngine);
        }
    }

    public class gear{
        void sportBike(){
            previewGear = "Cek kabel kopling. Cek mekanisme gear change.";
            textGear.setText(previewGear);
        }

        void moped(){
            previewGear = "Cek sistem CVT.";
            textGear.setText(previewGear);
        }

        void suv(){
            previewGear = "Cek mekanisme 4WD. Cek kabel kopling. Cek mekanisme gear change.";
            textGear.setText(previewGear);
        }

        void sedan(){
            previewGear = "Cek sistem gear change.";
            textGear.setText(previewGear);
        }
    }
}