package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class after_catagory_clicked extends AppCompatActivity implements View.OnClickListener{

    private Button crops_button, fruits_button, fish_button, animal_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_catagory_clicked);

        //TextView freq = (TextView) findViewById(R.id.freq);
        crops_button = (Button)findViewById(R.id.Crops_button);
        fruits_button = (Button)findViewById(R.id.Fruits_button);
        fish_button = (Button)findViewById(R.id.Fish_button);
        animal_button = (Button)findViewById(R.id.Animal_button);

        crops_button.setOnClickListener(this);
        fruits_button.setOnClickListener(this);
        fish_button.setOnClickListener(this);
        animal_button.setOnClickListener(this);
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

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.Crops_button)
        {
            Intent intent = new Intent(after_catagory_clicked.this, after_crop_clicked.class);
            startActivity(intent);
        }

        else if(id==R.id.Animal_button)
        {
            Intent intent = new Intent(after_catagory_clicked.this, after_animal_clicked.class);
            startActivity(intent);
        }

        else if(id==R.id.Fish_button)
        {
            Intent intent = new Intent(after_catagory_clicked.this, after_fish_clicked.class);
            startActivity(intent);
        }

        else if(id==R.id.Fruits_button)
        {
            Intent intent = new Intent(after_catagory_clicked.this, after_fruits_clicked.class);
            startActivity(intent);
        }
    }
}

