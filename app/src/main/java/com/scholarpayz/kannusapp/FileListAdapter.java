package com.scholarpayz.kannusapp;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.Locale;


public class FileListAdapter extends BaseAdapter {
    private final static String TAG = FileListAdapter.class.getSimpleName();
    Context context;
    File[] files;
    LayoutInflater inflater;

    FileListAdapter(Context applicationContext, File[] files) {
        this.context = applicationContext;
        this.files = files;
        this.inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int position) {
        return this.files[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static String convertMillisecondsToTime(long milliseconds) {
        long minutes = (milliseconds / 1000) / 60;
        long seconds = (milliseconds / 1000) % 60;
        String text = "";

        if (seconds != 0) {
            text = String.format(Locale.ENGLISH, "%d sec", seconds);
        }

        if (minutes != 0) {
            text = String.format(Locale.ENGLISH, "%d min %s", minutes, text);
        }

        return text;
    }

    private static String getDuration(String path) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(path);
        String durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        return convertMillisecondsToTime(Long.parseLong(durationStr));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_file_list, null);
        File file = this.files[position];
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView duraiton = (TextView) view.findViewById(R.id.duration);


        name.setText(file.getName());
        duraiton.setText(getDuration(file.getAbsolutePath()));

        return view;
    }
}
