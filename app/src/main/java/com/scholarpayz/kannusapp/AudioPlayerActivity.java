package com.scholarpayz.kannusapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity {
    String filePath = null;
    MediaPlayer mp = new MediaPlayer();
    ImageButton playButton, pauseButton, stopButton;

    private void stopAudio() {
        mp.stop();
        stopButton.setEnabled(false);
        playButton.setEnabled(true);
    }

    private void pauseAudio() {
        if (mp.isPlaying()) {
            mp.pause();

            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);
            playButton.setEnabled(true);
        } else {
            mp.start();

            pauseButton.setEnabled(true);
            playButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }

    private void playAudio() {
        try {
            mp.setDataSource(this.filePath);
            mp.prepare();
        } catch (IOException e) {
            return;
        }

        mp.start();

        playButton.setEnabled(false);
        stopButton.setEnabled(true);
        pauseButton.setEnabled(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        Intent intent = getIntent();
        filePath = intent.getStringExtra("file_path");

        playButton = (ImageButton) findViewById(R.id.play_icon);
        pauseButton = (ImageButton) findViewById(R.id.pause_icon);
        stopButton = (ImageButton) findViewById(R.id.stop_icon);

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });

        this.playAudio();
    }
}
