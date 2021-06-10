package com.example.tasbeehcounter.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.tasbeehcounter.R;

public class SettingActivity extends AppCompatActivity {

    Switch switch1;
    Switch switch2;

    RelativeLayout layout;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Window window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        preferences = getSharedPreferences("VIBRATE", MODE_PRIVATE);
        editor = preferences.edit();

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        layout = findViewById(R.id.settingActivity);
        Window window = this.getWindow();




        boolean dark_mode = preferences.getBoolean("Dm", false);
        if (dark_mode) {
            layout.setBackgroundColor(Color.DKGRAY);
            switch1.setTextColor(Color.WHITE);
            switch2.setTextColor(Color.WHITE);







            switch2.setChecked(true);

        } else {
            layout.setBackground(getResources().getDrawable(R.drawable.colors));
            switch1.setTextColor(Color.BLACK);
            switch2.setTextColor(Color.BLACK);



            switch2.setChecked(false);
        }


        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (!isChecked) {
                layout.setBackground(getResources().getDrawable(R.drawable.colors));
                    switch1.setTextColor(Color.BLACK);
                    switch2.setTextColor(Color.BLACK);



                    editor.putBoolean("Dm", false);
                } else {
                    layout.setBackgroundColor(Color.DKGRAY);
                    switch1.setTextColor(Color.WHITE);
                    switch2.setTextColor(Color.WHITE);







                    editor.putBoolean("Dm", true);

                }
                editor.commit();
            }

        });
    }
}
