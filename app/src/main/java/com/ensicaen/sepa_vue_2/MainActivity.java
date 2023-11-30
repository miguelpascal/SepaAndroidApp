package com.ensicaen.sepa_vue_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ensicaen.sepa_vue_2.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_main);
    }
}