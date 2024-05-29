package com.computer.tripsuitcase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;


//FRONT END SORUNLARI
@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Objects.requireNonNull(getSupportActionBar( )).hide();

        new Handler(  ).postDelayed(() -> {
            Intent intent = new Intent( SplashScreen.this,MainActivity.class );
            startActivity(intent);
            finish();
        },1400);
    }
}