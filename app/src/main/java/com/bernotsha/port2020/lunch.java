package com.bernotsha.port2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class lunch extends AppCompatActivity {
    ImageView lunchbefore,lunchafter;
    ImageButton lunchhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        lunchbefore = findViewById(R.id.lunchbefore);
        lunchafter = findViewById(R.id.lunchafter);
        lunchhome = findViewById(R.id.lunchhome);
        lunchhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        lunchbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),android.class));
                finish();
            }
        });
        lunchafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ignite.class));
                finish();
            }
        });
    }
}
