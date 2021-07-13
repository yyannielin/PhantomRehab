package com.example.phantomrehab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLevel extends AppCompatActivity implements View.OnClickListener {

    private Button Beginner;
    private Button Intermed;
    private Button Hard;
    private Button Return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        Beginner = findViewById(R.id.btn_beginner);
        Intermed = findViewById(R.id.btn_intermed);
        Hard = findViewById(R.id.btn_hard);
        Return = findViewById(R.id.btn_return);

        Beginner.setOnClickListener(this);
        Intermed.setOnClickListener(this);
        Hard.setOnClickListener(this);
        Return.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_beginner:
                startActivity(new Intent(this,BeginnerMain.class));
                break;
            case R.id.btn_intermed:
                startActivity(new Intent(this,IntermedMain.class));
                break;
            case R.id.btn_hard:
                startActivity(new Intent(this,HardMain.class));
                break;
            case R.id.btn_return:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}

