package com.example.bengkel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        button = (Button) findViewById(R.id.button);

        //jika button di click
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //mengambil value dari user.json
                    JSONObject objectJson = new JSONObject(loadJson());
                    JSONArray arrayJson = objectJson.getJSONArray("user");
                    for(int i = 0; i < arrayJson.length(); i++){
                        JSONObject object = arrayJson.getJSONObject(i);
                        String userUsername = object.getString("username");
                        String userPass = object.getString("password");

                        //jika username dan password terdaftar maka pindah screen
                        if(username.getText().toString().equals(userUsername) && password.getText().toString().equals(userPass)) {
                            Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                            openForm();
                        }
                        //jika username dan password tidak terdaftar maka keluar peringatan
                        else if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Please fill your username or password!", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //parse file dari user.json
    public String loadJson(){
        String json = null;
        try{
            InputStream is = this.getAssets().open("user.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    //pindah screen
    public void openForm() {
        Intent intent = new Intent(this, Form.class);
        startActivity(intent);
    }
}