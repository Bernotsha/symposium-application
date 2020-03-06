package com.bernotsha.port2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

public class register extends AppCompatActivity {
    Button click,internetok,onereg;
    TextView idno,tname,tcollege,tmobile,tdepartment,temail;
    private ConnectivityManager connectivityManager;
    EditText rname,rgender,rmobile,remail,rbranch,rdepartment,ryear,rcollege;
    CheckBox rignite,rspot,rblind,rkahoot,rphotography,rhunt,
    rsql,rpaper;
    Button rbutton;
    RadioButton radiob;
    DatabaseReference ref;
    RadioGroup radiogroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        final boolean firststart= prefs.getBoolean("firststart",true);
        if(!firststart)
        {
            notshowagain();
        }

        if(networkInfo!=null && networkInfo.isConnected())
        {
            FancyToast.makeText(register.this,"Connected to internet",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();

        }
        else
        {
            internet();
        }
        ref = FirebaseDatabase.getInstance().getReference();
        rname = findViewById(R.id.rname);
        rgender = findViewById(R.id.rgender);
        rmobile = findViewById(R.id.rmobile);
        remail=findViewById(R.id.remail);
        rbranch = findViewById(R.id.rbranch);
        rdepartment = findViewById(R.id.rdepartment);
        ryear = findViewById(R.id.ryear);
        rcollege = findViewById(R.id.rcollege);
        rignite = findViewById(R.id.rignite);
        rspot = findViewById(R.id.rspot);
        rblind = findViewById(R.id.rblind);
        radiogroup = findViewById(R.id.radiogroup);
        rkahoot = findViewById(R.id.rkahoot);
        rphotography = findViewById(R.id.rphotography);
        rhunt = findViewById(R.id.rhunt);
        rsql = findViewById(R.id.rsql);
        rpaper = findViewById(R.id.rpaper);
        rbutton = findViewById(R.id.rbutton);
        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!firststart)
                {
                    notshowagain();
                }
                else
                {
                    addArtist();
                }




            }
        });




    }
    public void showdialog(View view)
    {
        dialog();
    }
    public void inter(View view)
    {
        internet();

    }
    private void internet()
    {
        ViewGroup v = findViewById(R.id.content);
        View Dialog = LayoutInflater.from(this).inflate(R.layout.nointernet,v,false);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setView(Dialog);
        final AlertDialog alertDialog=b.create();
        internetok =Dialog.findViewById(R.id.internetok);
        internetok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
                alertDialog.dismiss();
            }
        });
        alertDialog.show();


    }

    private void dialog() {
        ViewGroup viewGroup=findViewById(R.id.content);
        View DialogView= LayoutInflater.from(this).inflate(R.layout.successfuldialog,viewGroup,false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(DialogView);

        final AlertDialog alertDialog=builder.create();

        click=DialogView.findViewById(R.id.successful);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    private void notshowagain()
    {
    ViewGroup viewGroup = findViewById(R.id.content);
    View DialogView = LayoutInflater.from(this).inflate(R.layout.oneregister,viewGroup,false);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(DialogView);
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        String idn = prefs.getString("keyid","Tryagain");
        String mobilen = prefs.getString("pmobile","tryagain");
        String collegen = prefs.getString("pcollege","tryagain");
        String namen = prefs.getString("pname","tryagain");
        String departmentn = prefs.getString("pdepartment","tryagain");
        String emailn = prefs.getString("pemail","tryagain");
        idno=DialogView.findViewById(R.id.idno);
        tname = DialogView.findViewById(R.id.tname);
        tcollege = DialogView.findViewById(R.id.tcollege);
        tmobile = DialogView.findViewById(R.id.tmobile);
        temail = DialogView.findViewById(R.id.temail);
        tdepartment = DialogView.findViewById(R.id.tdepartment);
        idno.setText(String.format("Id is %s", idn));
        tname.setText(String.format("Name : %s",namen));
        tcollege.setText(String.format("College : %s",collegen));
        tmobile.setText(String.format("Mobile : %s",mobilen));
        tdepartment.setText(String.format("Department : %s",departmentn));
        temail.setText(String.format("Email : %s",emailn));
    final AlertDialog al = builder.create();
    onereg = DialogView.findViewById(R.id.onereg);
    onereg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(),Navigation.class));
            al.dismiss();
        }
    });
    al.show();
    }

    public void addArtist()
    {
        String id=ref.push().getKey();
        String rname1,rgender1,remail1,rmobile1,rbranch1,rdepartment1,ryear1,rcollege1,
                rmachine1="NO",randroid1="NO",rignite1="NO",rspot1="NO",rblind1="NO",rkahoot1="NO",rphotography1="NO",rhunt1="NO",
                rsql1="NO",rpaper1="NO";
        rname1=rname.getText().toString().trim();
        rgender1=rgender.getText().toString().trim();
        rmobile1 = rmobile.getText().toString().trim();
        rbranch1=rbranch.getText().toString().trim();
        rdepartment1=rdepartment.getText().toString().trim();
        rcollege1=rcollege.getText().toString().trim();
        ryear1 = ryear.getText().toString().trim();
        remail1 = remail.getText().toString().trim();

        if(TextUtils.isEmpty(rname1))
        {
            rname.setError("Enter your name");
            rname.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(rgender1))
        {
            rgender.setError("Specify your gender");
            rgender.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(remail1))
        {
            remail.setError("Required");
            remail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(rmobile1)|| rmobile1.length()!=10)
        {
            rmobile.setError("Enter Correct number");
            rmobile.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(rdepartment1))
        {
            rdepartment.setError("Enter your department");
            rdepartment.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(ryear1))
        {
            ryear.setError("Enter your name");
            ryear.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(rbranch1))
        {
            rbranch.setError("Enter your Branch");
            rbranch.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(rcollege1))
        {
            rcollege.setError("Enter your College");
            rcollege.requestFocus();
            return;
        }

        if(rignite.isChecked())
        {
            rignite1="YES";
        }
        if(rspot.isChecked())
        {
            rspot1="YES";
        }
        if(rblind.isChecked())
        {
            rblind1="YES";
        }

        randroid1="no";


        rmachine1="no";


        if(rkahoot.isChecked())
        {
            rkahoot1="YES";
        }
        if(rphotography.isChecked())
        {
            rphotography1="YES";
        }
        if(rhunt.isChecked())
        {
            rhunt1="YES";
        }
        if(rsql.isChecked())
        {
            rsql1="YES";
        }
        if(rpaper.isChecked())
        {
            rpaper1="YES";
        }
        reg r = new reg(id,rname1,rgender1,remail1,rmobile1,rbranch1,rdepartment1,ryear1,rcollege1,rmachine1,randroid1,
                rignite1,rspot1,rblind1,rkahoot1,rphotography1,rhunt1,rsql1,rpaper1);
        ref.child("Registration").child(rmobile1).setValue(r);
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putBoolean("firststart",false);
        editor.putString("keyid",id);
        editor.putString("pname",rname1);
        editor.putString("pcollege",rcollege1);
        editor.putString("pdepartment",rdepartment1);
        editor.putString("pmobile",rmobile1);
        editor.putString("pemail",remail1);


        editor.apply();

        dialog();






    }

}
