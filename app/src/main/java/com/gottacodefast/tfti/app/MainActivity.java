package com.gottacodefast.tfti.app;

import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;



public class MainActivity extends ActionBarActivity {

    MediaPlayer mp;
    TextView tfti, brian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = new MediaPlayer();
        tfti = (TextView) findViewById(R.id.custom_font);
        brian = (TextView)findViewById(R.id.custom_font);
        Typeface font = Typeface.createFromAsset(getAssets(), "justgirlythings.ttf");
        tfti.setTypeface(font);
        brian.setTypeface(font);
        /*
        Button b = (Button) findViewById(R.id.button1);

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(mp.isPlaying())
                {
                    mp.stop();
                    mp.reset();
                }
                try {
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("tfti.mp3");
                    mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }); */

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.reset();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if(mp.isPlaying())
        {
            mp.stop();
            mp.reset();
        }
        try {
            AssetFileDescriptor afd;
            afd = getAssets().openFd("tfti.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStop() {
        mp.release();
        super.onStop();
    }
}
