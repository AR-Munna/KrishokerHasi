package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class after_crop_clicked extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[10];
    private static TextView[] TextViewArray = new TextView[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_crop_clicked);

        setButtonArray();
        setTextViewArray();

        int noOfItems = GetnoOfItems(0);

        for(int i=noOfItems; i<10; i++)
        {
            buttonArray[i].setVisibility(View.GONE);
            TextViewArray[i].setVisibility(View.GONE);
        }

        for (int i=0; i<noOfItems; i++) buttonArray[i].setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.rice_button)
        {
            Intent intent = new Intent(after_crop_clicked.this, common_page.class);
            startActivity(intent);
        }
    }

    /*------------------this function will return no. of items for crops from sqlite database-------------*/

    public int GetnoOfItems(int id)
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Cursor res = databaseHelper.getAllData(id+500);
        if(res.getCount()==0)
            return 0;

        StringBuffer buffer = new StringBuffer();

        while(res.moveToNext())
            buffer.append(res.getString(1));

        return Integer.parseInt(buffer.toString());
    }

    /*-------------------------------------this function is used to set buttonArray-----------------------*/

    public void setButtonArray()
    {
        buttonArray[0] = (Button)findViewById(R.id.rice_button);
        buttonArray[1] = (Button)findViewById(R.id.wheat_button);
        buttonArray[2] = (Button)findViewById(R.id.corn_button);
        buttonArray[3] = (Button)findViewById(R.id.potato_button);
        buttonArray[4] = (Button)findViewById(R.id.jute_button);
        buttonArray[5] = (Button)findViewById(R.id.sugarcane_button);
        buttonArray[6] = (Button)findViewById(R.id.brinjal_button);
        buttonArray[7] = (Button)findViewById(R.id.potol_button);
        buttonArray[8] = (Button)findViewById(R.id.onion_button);
        buttonArray[9] = (Button)findViewById(R.id.morich_button);

    }

    /*-------------------------------------this function is used to set textArray-----------------------*/

    public void setTextViewArray()
    {
        TextViewArray[0] = (TextView) findViewById(R.id.rice_text);
        TextViewArray[1] = (TextView) findViewById(R.id.wheat_text);
        TextViewArray[2] = (TextView) findViewById(R.id.corn_text);
        TextViewArray[3] = (TextView) findViewById(R.id.potato_text);
        TextViewArray[4] = (TextView) findViewById(R.id.jute_text);
        TextViewArray[5] = (TextView) findViewById(R.id.sugarcane_text);
        TextViewArray[6] = (TextView) findViewById(R.id.brinjal_text);
        TextViewArray[7] = (TextView) findViewById(R.id.potol_text);
        TextViewArray[8] = (TextView) findViewById(R.id.onion_text);
        TextViewArray[9] = (TextView) findViewById(R.id.morich_text);
    }
}
