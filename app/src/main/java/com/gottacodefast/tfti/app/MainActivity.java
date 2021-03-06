package com.gottacodefast.tfti.app;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.media.MediaPlayer;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    TextView tfti, brian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tfti  = (TextView) findViewById(R.id.custom_font);
        brian = (TextView) findViewById(R.id.brian_font);
        Typeface font = Typeface.createFromAsset(getAssets(), "justgirlythings.ttf");
        tfti.setTypeface(font);
        brian.setTypeface(font);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        //Garbage collection happens after onPause, so release mp
        super.onPause();
    }

    @Override
    protected void onResume() {
        //GC deallocated mp, so reallocate as new MediaPlayer
        super.onResume();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        try {
            MediaPlayer mp = new MediaPlayer();
            AssetFileDescriptor afd;
            afd = getAssets().openFd("tfti.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } catch (IllegalStateException err) {
            err.printStackTrace();
            return false;
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), Settings.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


