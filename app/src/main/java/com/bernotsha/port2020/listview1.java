package com.bernotsha.port2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;

public class listview1 extends AppCompatActivity {
    Button internetok;
    private ConnectivityManager connectivityManager;
    ImageButton listhome;
    ImageView listbefore,listafter;
    private RecyclerView mRecyclerview;
    private DatabaseReference mRef;
    FirebaseDatabase mfireDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview1);
        listhome = findViewById(R.id.listhome);
        connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        listbefore = findViewById(R.id.listbefore);
        listafter = findViewById(R.id.listafter);
        if(networkInfo!=null && networkInfo.isConnected())
        {
            FancyToast.makeText(listview1.this,"Wait for few seconds",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();

        }
        else
        {
            internet();
        }
        mRef= FirebaseDatabase.getInstance().getReference().child("Notification");
        mRef.keepSynced(true);
        mRecyclerview = (RecyclerView)findViewById(R.id.recyclerview);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mfireDatabase = FirebaseDatabase.getInstance();

        listhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Navigation.class));
                finish();
            }
        });
        listafter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ignite.class));
                finish();
            }
        });
        listbefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
                finish();
            }
        });



    }

    private void internet() {
            ViewGroup v = findViewById(R.id.content);
            View Dialog = LayoutInflater.from(this).inflate(R.layout.nointernet,v,false);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setView(Dialog);
            final AlertDialog alertDialog=b.create();
            internetok =Dialog.findViewById(R.id.internetok);
            internetok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),listview1.class));
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog,ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Blog, ViewHolder>(
                Blog.class,
                R.layout.blow_row,
                ViewHolder.class,
                mRef
        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Blog blog, int i) {
                viewHolder.setDetails(getApplicationContext(),blog.getTitle(),blog.getDesc(),blog.getImage());
            }
        };
        mRecyclerview.setAdapter(firebaseRecyclerAdapter);

    }
}

