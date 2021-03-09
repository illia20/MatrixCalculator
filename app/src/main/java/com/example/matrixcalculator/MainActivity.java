package com.example.matrixcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void startDeterminant(View view){
        Intent intent = new Intent(this, DeterminantActivity.class);
        startActivity(intent);
    }

    public void startInverse(View view){
        Intent intent = new Intent(this, InverseActivity.class);
        startActivity(intent);
    }
    public void startAddSub(View view){
        Intent intent = new Intent(this, AddSubActivity.class);
        startActivity(intent);
    }
    public void startMult(View view){
        Intent intent = new Intent(this, MultiplyActivity.class);
        startActivity(intent);
    }
    public void startGraphics(View view){
        Intent intent = new Intent(this, HeatmapActivity.class);
        startActivity(intent);
    }
}