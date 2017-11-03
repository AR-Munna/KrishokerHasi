package com.example.pranto.krishokerhasi.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.MainActivity_socialnetwork;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.PostActivity;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.RegisterActivity;
import com.example.pranto.krishokerhasi.socialnetwork.ui.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mainpage);

        //first time app install dile frequency reset kore dea hbe
        FirstTimeRun();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void FirstTimeRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            refreshfrequency();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_mainpage_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    /*--------------------app theke exit korar jonno "exitFunction" lekha hoise------------------------*/

    public void exitFunction()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("আপনি কি অ্যাপ থেকে বের হতে চান ???");
        builder.setCancelable(true);
        builder.setNegativeButton("না", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("হ্যাঁ!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*-------------------------- End part of "exitFunction"----------------------------------------------------*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*--------------------catagory button e click korle catagory gula show korbe-------------------*/

        if (id == R.id.catagory)
        {
            Intent intent = new Intent(MainActivity.this, after_catagory_clicked.class);
            startActivity(intent);
        }

        /*---------------------exit button er kaj eikhane hobe-----------------------------------------*/

        else if (id == R.id.exit)
        {
            exitFunction();
        }



        else if (id == R.id.post)
        {
            Intent intent = new Intent(MainActivity.this, MainActivity_socialnetwork.class);
            startActivity(intent);
        }

        else if (id == R.id.update)
        {
            Intent intent = new Intent(MainActivity.this, after_update_clicked.class);
            startActivity(intent);
        }

        /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //catagory button er frequency reset kora
    void refreshfrequency() {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        dbh.db.execSQL("DROP TABLE IF EXISTS " + dbh.table);
        dbh.db.execSQL("create table " + dbh.table + " (ID INTEGER,INFO TEXT)");
        int[] arr = {0, 0, 0, 0};

        for (int i = 0; i < 4; i++) {
            boolean isInseted = dbh.insertData(i + 1000, String.valueOf(arr[i]));
        }
    }
}
