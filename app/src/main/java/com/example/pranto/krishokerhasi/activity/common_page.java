package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pranto.krishokerhasi.R;
import com.example.pranto.krishokerhasi.socialnetwork.ui.activities.MainActivity_socialnetwork;

public class common_page extends AppCompatActivity implements View.OnClickListener{

    private static Button[] buttonArray = new Button[7];
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_page);

        setButtonArray();

        for (int i=0; i<7; i++) buttonArray[i].setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id==R.id.audio_button)
        {
            Intent intent = new Intent(common_page.this, audio_file.class);
            startActivity(intent);
        }
        else if(id==R.id.ask_button)
        {
            Intent intent = new Intent(common_page.this, text_search.class);
            startActivity(intent);
        }

        else if(id == R.id.hotline_button)
        {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:+8801780552894"));
            startActivity(callIntent);
        }

        else if(id == R.id.share_button)
        {
            int from_where = 1;
            Intent intent = new Intent(common_page.this, MainActivity_socialnetwork.class);
            intent.putExtra("from_where", from_where);
            startActivity(intent);
        }

        else if(id==R.id.homebutton)
        {
            Intent intent = new Intent(common_page.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void setButtonArray()
    {
        buttonArray[0] = (Button)findViewById(R.id.text_button); //text_button
        buttonArray[1] = (Button)findViewById(R.id.audio_button); //audio_button
        buttonArray[2] = (Button)findViewById(R.id.ask_button); //ask_button
        buttonArray[3] = (Button)findViewById(R.id.hotline_button); //hotline_button
        buttonArray[4] = (Button)findViewById(R.id.notification_button); //notification_button
        buttonArray[5] = (Button)findViewById(R.id.share_button); //share_button
        buttonArray[6] = (Button)findViewById(R.id.homebutton); //share_button
    }
}
