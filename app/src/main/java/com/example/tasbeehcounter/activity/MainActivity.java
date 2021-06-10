package com.example.tasbeehcounter.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tasbeehcounter.R;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.fragment.HomeFragment;
import com.example.tasbeehcounter.fragment.ListFragment;
import com.example.tasbeehcounter.fragment.OnBackPressed;
import com.example.tasbeehcounter.fragment.SetTarget;
import com.example.tasbeehcounter.model.Counter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import static com.example.tasbeehcounter.fragment.HomeFragment.counter;
import static com.example.tasbeehcounter.fragment.HomeFragment.editText;
import static com.example.tasbeehcounter.fragment.HomeFragment.textView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    AlertDialog.Builder builder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    boolean doubleBackToExitPressedOnce = false;
    private Object ListFragment;
    private Object SetTarget;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing id's
        bottomNavigationView = findViewById(R.id.bottom_nav);
        sharedPreferences = getSharedPreferences("VALUE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        builder = new AlertDialog.Builder(this);


        show(new HomeFragment());


        //working with bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;

                switch (item.getItemId()) {

                    case R.id.mnu_home:

                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                        fragment = new HomeFragment();
                        break;
                    case R.id.mnu_list:

                        Toast.makeText(MainActivity.this, "List", Toast.LENGTH_SHORT).show();
                        fragment = new ListFragment();
                        break;
                    case R.id.mnu_total:

                        Toast.makeText(MainActivity.this, "target", Toast.LENGTH_SHORT).show();
                        fragment = new SetTarget();
                        break;
                    default:
                        fragment = new HomeFragment();
                }
                show(fragment);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.setting, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.setting:

                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            case R.id.Rate_app:

                break;


        }
        return true;
    }

    public void show(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {


//       tellFragments();
//       super.onBackPressed();



        if(doubleBackToExitPressedOnce)
        {
        super.onBackPressed();
        return;
        }
        this.doubleBackToExitPressedOnce =true;
        Toast.makeText(this,"click again to exit",Toast.LENGTH_SHORT).

    show();
        new Handler().

    postDelayed(new Runnable() {

        @Override
        public void run () {
            doubleBackToExitPressedOnce = false;
        }
    },
            2000);

    }
//    private void tellFragments() {
//
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);
//        if (!(fragment instanceof OnBackPressed) || !((OnBackPressed) fragment).onBackPressed()) {
//            super.onBackPressed();
//        }
//    }


}
