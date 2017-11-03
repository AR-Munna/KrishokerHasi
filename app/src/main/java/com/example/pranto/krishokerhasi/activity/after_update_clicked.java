package com.example.pranto.krishokerhasi.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class after_update_clicked extends AppCompatActivity {

    Button button1,button2,button3;
    TextView textView;
    EditText editText;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_update_clicked);

        textView = (TextView)findViewById(R.id.tv);
        editText = (EditText)findViewById(R.id.edittext1);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetOnlineData(0);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetOfflineData(0);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int query = Integer.parseInt(editText.getText().toString());
                GetOfflineData(query);

            }
        });
    }

    //net asche ki nai dekhe data niya ase
    void GetInformation(int i)
    {
        if(IsNetAvailable())
            GetOnlineData(i);
        else
            GetOfflineData(i);
    }

    //index er data firebase thk niya ase
    void GetOnlineData(int i)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("data/"+String.valueOf(i));
        //mDatabase = FirebaseDatabase.getInstance().getReference().child("option"+"0"+"/"+"0");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString() ;
                CreateIntent(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void GetOfflineData(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        boolean empty=false;

        Cursor res = dbh.getAllData(i);
        if(res.getCount()==0) {
            empty = true;
            //showMessage("Error","Nothing");
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {buffer.append(res.getString(1)+"\n\n");}

        String getrec=buffer.toString();

        if(empty)
            getrec = "Empty";

        CreateIntent(getrec);
    }

    void ClearDataBase()
    {
        final DatabaseHelper dbh = new DatabaseHelper(this);
        int[] TempArray = new int[4];
        TempArray = load_frequency();
        dbh.db.execSQL("DROP TABLE IF EXISTS "+dbh.table);
        dbh.db.execSQL("create table "+dbh.table+" (ID INTEGER,INFO TEXT)");
        save_frequency(TempArray);
    }


    void CreateIntent(String data)
    {
        Intent intent = new Intent(after_update_clicked.this, InfoPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("stuff", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    boolean IsNetAvailable()
    {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            return true;
        return false;
    }

    void ToastMethod(String str)
    {
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();}




    int Getfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+1000);
        if(res.getCount()==0) {
            return 0;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
        {
            //buffer.append("ID :" + res.getString(0)+"\n");
            buffer.append(res.getString(1));
        }
        //showMessage("Data",buffer.toString());

        return Integer.parseInt(buffer.toString());
    }

    void save_frequency(int[] a)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        for (int i = 0; i < 4; i++) {
            boolean isInseted = dbh.insertData(i+1000, String.valueOf(a[i]));
        }
    }

    int[] load_frequency()
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        int[] a = new int[4];
        for (int i = 0; i < 4; i++) {
            a[i] = Getfrequency(i);
        }
        return a;
    }

}
