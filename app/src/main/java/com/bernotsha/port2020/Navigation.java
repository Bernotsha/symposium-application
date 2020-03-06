package com.bernotsha.port2020;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView medal;
    Button ok;
    private ConnectivityManager connectivityManager;
    FirebaseAuth fauth;
    DatabaseReference ref;
    String value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fauth=FirebaseAuth.getInstance();
        FloatingActionButton fab = findViewById(R.id.fab);
        medal = findViewById(R.id.medal);
        ref= FirebaseDatabase.getInstance().getReference().child("Link");
        connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            FancyToast.makeText(Navigation.this,"Connected to internet",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS, false).show();
        }
        else
        {
            FancyToast.makeText(Navigation.this,"Not Connected to internet",FancyToast.LENGTH_SHORT,FancyToast.INFO, false).show();
        }





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Calling to Chairman", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(Navigation.this,call.class);
                i.putExtra("key","7904132300");
                startActivity(i);
                finish();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),about.class));
            finish();
        }
        else if(id==R.id.uninstallsettings)
        {
            Intent intent = new Intent(Intent.ACTION_DELETE);
            intent.setData(Uri.parse("package:com.example.port_2020"));
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.register) {
            startActivity(new Intent(getApplicationContext(),register.class));
            finish();
        } else if (id == R.id.lsv) {
            startActivity(new Intent(getApplicationContext(),listview1.class));
            finish();

        }
        else if (id == R.id.nml) {
            startActivity(new Intent(getApplicationContext(),ml.class));
            finish();
        }
        else if (id == R.id.nandroid) {
            startActivity(new Intent(getApplicationContext(),android.class));
            finish();

        }else if (id == R.id.ignite) {
            startActivity(new Intent(getApplicationContext(),ignite.class));
            finish();

        } else if (id == R.id.nkahoot) {
            startActivity(new Intent(getApplicationContext(),kahoot.class));
            finish();

        } else if (id == R.id.nspoterror) {
            startActivity(new Intent(getApplicationContext(),spot.class));
            finish();

        }
        else if (id==R.id.nshortfilm)
        {
            startActivity(new Intent(getApplicationContext(),shortfilm.class));
            finish();
        }
        else if (id == R.id.nblindcoding) {
            startActivity(new Intent(getApplicationContext(),blind.class));
            finish();

        }
        else if(id==R.id.ncoding)
        {
            startActivity(new Intent(getApplicationContext(),coding.class));
            finish();

        }
        else if (id == R.id.nphotography) {
            startActivity(new Intent(getApplicationContext(),photography.class));
            finish();

        }
        else if (id == R.id.nhuntem) {
            startActivity(new Intent(getApplicationContext(),hunt.class));
            finish();

        }
        else if (id==R.id.catalog)
        {
            startActivity(new Intent(getApplicationContext(),lunch.class));
            finish();
        }
        else if (id == R.id.nsql) {
            startActivity(new Intent(getApplicationContext(),sql.class));
            finish();

        }else if (id == R.id.npaper) {
            startActivity(new Intent(getApplicationContext(),paper.class));
            finish();

        } else if (id == R.id.call){

            Intent i = new Intent(Navigation.this,call.class);
            i.putExtra("key","7904132300");
            startActivity(i);
            finish();
        }
        else if (id==R.id.office)
        {

                startActivity(new Intent(getApplicationContext(),office1.class));
                finish();
        }
        else if (id==R.id.nshare)
        {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plane");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     value = dataSnapshot.child("linkid").getValue().toString();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            String sharebody = "Your App name";
            String sub = value;
            i.putExtra(Intent.EXTRA_SUBJECT,sharebody);
            i.putExtra(Intent.EXTRA_TEXT,sub);
            startActivity(Intent.createChooser(i,"Share using"));
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
