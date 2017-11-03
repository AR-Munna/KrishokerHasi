package com.example.pranto.krishokerhasi.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class after_catagory_clicked extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_catagory_clicked);

        //TextView freq = (TextView) findViewById(R.id.freq);

    }

    int Getfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);

        Cursor res = dbh.getAllData(i+1000);
        if(res.getCount()==0) {
            return 0;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext())
            buffer.append(res.getString(1));
        return Integer.parseInt(buffer.toString());
    }

    void addfrequency(int i)
    {
        DatabaseHelper dbh = new DatabaseHelper(this);
        int freq = Getfrequency(i)+1;
        boolean isUpdated = dbh.UpdateData(i+1000, String.valueOf(freq));
    }


}

