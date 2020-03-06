package com.bernotsha.port2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class android extends AppCompatActivity {
    ImageView androidbefore, androidafter;
    ImageButton androidhome;
    private static final int requestcallcode = 1;
    ImageView androidphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        androidhome = findViewById(R.id.androidhome);
        androidafter = findViewById(R.id.androidafter);
        androidbefore = findViewById(R.id.androidbefore);
        androidphone = findViewById(R.id.androidphone);
        androidphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.this,call.class);
                i.putExtra("key","7904132300");
                startActivity(i);
                finish();
            }
        });
        androidhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Navigation.class));
                finish();
            }
        });
        androidafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), lunch.class));
                finish();
            }
        });
        androidbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ml.class));
                finish();
            }
        });

    }


}
