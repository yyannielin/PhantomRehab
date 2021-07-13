package com.example.phantomrehab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HardMain extends AppCompatActivity {

    private Button Next;
    private Button Ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        Next = findViewById(R.id.btn_next);
        Ret = findViewById(R.id.btn_return);

    }

    public void ret(View view) {
        startActivity(new Intent(getApplicationContext(), ChooseLevel.class));
    }

    public void callCamera(View view) {
        startActivity(new Intent("android.media.action.IMAGE_CAPTURE"));
    }
}