package com.scholarpayz.kannusapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton gm = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        gm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecordingActivity.class);
                startActivity(intent);
            }
        });

        getStoredRecordings();
    }

    private void getStoredRecordings() {
        File directory = new File(getFilesDir().getAbsolutePath());
        File[] files = directory.listFiles();

        FileListAdapter fileListAdapter = new FileListAdapter(MainActivity.this, files);

        ListView listView = findViewById(R.id.file_list);
        listView.setAdapter(fileListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AudioPlayerActivity.class);
                intent.putExtra("file_path", files[position].getAbsolutePath());
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
}