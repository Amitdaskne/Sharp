package com.sharp.pro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends Activity {
    static {
        System.loadLibrary("native");
    }
    private ProgressBar progressBar;
    private Timer timer;
    private int i=0;
    private static native String Dev();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        TextView dev = findViewById(R.id.dev);
        dev.setText(Dev());
        dev.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/font.ttf"));
        progressBar = findViewById(R.id.progressBar);
        timer = new Timer();
        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (i < 300) {
                        runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        progressBar.setProgress(i);
                        i++;
                    } else {
                        timer.cancel();
                        Intent intent =new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 0, 5);
    }
}
