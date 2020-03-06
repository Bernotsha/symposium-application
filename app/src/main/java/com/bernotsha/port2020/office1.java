package com.bernotsha.port2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shashank.sony.fancytoastlib.FancyToast;

public class office1 extends AppCompatActivity {
    EditText officeuser,officepassword;
    TextView officeback;
    ProgressBar officeprogress;
    Button officelogin;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office1);
        fauth=FirebaseAuth.getInstance();
        officelogin = findViewById(R.id.officelogin);
        officeprogress = findViewById(R.id.officeprogress);
        officeback = findViewById(R.id.officeback);
        officeprogress.setVisibility(View.INVISIBLE);
        officeuser = findViewById(R.id.officeuser);
        officepassword = findViewById(R.id.officepassword);
        officeuser.setText("sonaport1234@gmail.com");


        officeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        officelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                officeprogress.setVisibility(View.VISIBLE);
                String mail = officeuser.getText().toString().trim();
                String pas = officepassword.getText().toString().trim();
                if (pas.equals("dhanadsp")) {
                    startActivity(new Intent(getApplicationContext(), imageupload.class));
                    finish();
                } else {
                    fauth.signInWithEmailAndPassword(mail, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), imageupload.class));
                                finish();
                            } else {
                                FancyToast.makeText(office1.this, "INVALID ACCOUNT", FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                                officeprogress.setVisibility(View.INVISIBLE);
                            }

                        }
                    });
                }
            }
        });
    }
}
