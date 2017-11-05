package com.example.pranto.krishokerhasi.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pranto.krishokerhasi.R;

public class audio_file extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer mp;
    public static int pressCount = 0, flag;
    Button play_button;

    boolean isBackbuttonPressed = false;

    MediaPlayer mediaPlayer[] = new MediaPlayer[40];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_file);

        flag = getIntent().getIntExtra("flag", 0);

        play_button = (Button)findViewById(R.id.start_button);

        play_button.setOnClickListener(this);

        //mp = MediaPlayer.create(audio_file.this, R.raw.maine);

        //onBackPressed();
    }


    @Override
    public void onBackPressed() {
        pressCount = 0;
        mp.stop();
        Intent intent = new Intent(audio_file.this, common_page.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onClick(View view)
    {
        int id = view.getId();

        if(id == R.id.start_button)
        {
            if(pressCount == 0)
            {
                pressCount = 1;
                play_button.setBackgroundResource(R.mipmap.music_pause1);
                mp.start();
            }
            else if(pressCount == 1)
            {
                pressCount = 0;
                play_button.setBackgroundResource(R.mipmap.music_palyer1);
                mp.pause();
            }
        }
    }

    public void setMusic()
    {

    }
}
