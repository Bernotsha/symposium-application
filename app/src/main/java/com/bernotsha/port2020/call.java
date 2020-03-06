package com.bernotsha.port2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class call extends AppCompatActivity {
    private static final int requestcallcode=1;
    Button callhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        callhome = findViewById(R.id.callhome);
        callhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        makephonecall();
    }



    private void makephonecall() {
        String number =getIntent().getStringExtra("key");
        if(ContextCompat.checkSelfPermission(call.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(call.this,new String[]{Manifest.permission.CALL_PHONE},requestcallcode);
        }
        else
        {
            String dial = "tel:"+number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));

        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==requestcallcode)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                makephonecall();
            }
        }
    }
}
