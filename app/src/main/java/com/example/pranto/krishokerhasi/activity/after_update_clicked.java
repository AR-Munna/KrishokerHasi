package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pranto.krishokerhasi.R;

public class after_update_clicked extends AppCompatActivity {

    Button button1,button2,button3;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_update_clicked);

        textView = (TextView)findViewById(R.id.tv);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    void CreateIntent(String data)
    {
        Intent intent = new Intent(after_update_clicked.this, InfoPage.class);
        Bundle bundle = new Bundle();
        bundle.putString("stuff", data);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
