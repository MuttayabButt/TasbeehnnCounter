package com.example.tasbeehcounter.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tasbeehcounter.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;

    Timer mTimer;
    TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefs= getSharedPreferences("prefs",0);
        boolean firstRun = prefs.getBoolean("firstRun",false);
        if (firstRun == false){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstRun", true);
            editor.commit();
            Intent i=new Intent(SplashActivity.this,MySplash.class);
            startActivity(i);
            finish();
        }
        else {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);
            imageView =findViewById(R.id.iv2);

            getSupportActionBar().hide();

            imageView.animate()
                    .alpha(1)
                    .scaleX(1f)
                    .scaleY(1f)
                    .rotation(360)
                    .setDuration(1500);

            if (savedInstanceState == null) {
                getFragmentManager().beginTransaction()
                        .add(R.id.splash, new PlaceholderFragment())
                        .commit();

                timerTask = new TimerTask() {

                    @Override
                    public void run() {

                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                        // start the activity (Login/Home) depends on the login
                        // status
                    }
                };
                mTimer = new Timer();
                mTimer.schedule(timerTask, 3000);

            }
        }

    }
        public static class PlaceholderFragment extends Fragment {

            public PlaceholderFragment() {
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView =inflater.inflate(R.layout.activity_splash,
                        container, false);
                return rootView;
            }
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // cancel the timer if user has pressed the back button to abort it.
        if(mTimer != null)
            mTimer.cancel();
    }

}