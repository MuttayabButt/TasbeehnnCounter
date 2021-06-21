package com.example.tasbeehcounter.activity;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tasbeehcounter.R;
import com.example.tasbeehcounter.adapter.DrawerAdapter;
import com.example.tasbeehcounter.adapter.DrawerItem;
import com.example.tasbeehcounter.adapter.SimpleItem;
import com.example.tasbeehcounter.adapter.SpaceItem;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.fragment.HomeFragment;
import com.example.tasbeehcounter.fragment.ListFragment;
import com.example.tasbeehcounter.fragment.OnBackPressed;
import com.example.tasbeehcounter.fragment.SetTarget;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemSelectedListener {

    private static final int POS_CLOSE = 0;
    private static final int POS_HOME = 1;
    private static final int POS_LIST = 2;
    private static final int POS_INTELLIGENT = 3;
    private static final int POS_TARGET = 4;
    private static final int POS_SETTINGS = 5;
    private static final int POS_RATE_APP = 6;
    private static final int POS_ABOUT_US = 7;

    private String[] screenTitles;
    private Drawable[] screenIcons;

    private SlidingRootNav slidingRootNav;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withDragDistance(180)
                .withRootViewScale(0.75f)
                .withRootViewElevation(25)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer_menu)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_CLOSE),
                createItemFor(POS_HOME).setChecked(true),
                createItemFor(POS_LIST),
                createItemFor(POS_INTELLIGENT),
                createItemFor(POS_TARGET),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_RATE_APP),
                createItemFor(POS_ABOUT_US)
        ));
        adapter.setListener(this);

        RecyclerView recyclerView = findViewById(R.id.drawer_list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setSelectable(POS_HOME);

        //initializing id's
        bottomNavigationView = findViewById(R.id.bottom_nav);
        sharedPreferences = getSharedPreferences("VALUE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        builder = new AlertDialog.Builder(this);


        //show(new HomeFragment());


        //working with bottom navigation
       /* bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
        });*/

    }

    private DrawerItem createItemFor(int position){
        return new SimpleItem(screenIcons[position],screenTitles[position])
                .withIconTint(color(R.color.purple_700))
                .withTextTint(color(R.color.purple_700))
                .withSelectedIconTint(color(R.color.teal_700))
                .withSelectedTextTint(color(R.color.teal_700));
    }

    @ColorInt
    private int color(@ColorRes int res){
        return ContextCompat.getColor(this,res);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.id_actvityScreenIcons);
        Drawable[] icons = new Drawable[typedArray.length()];
        for (int i = 0; i < typedArray.length(); i++){
            int id = typedArray.getResourceId(i, 0);
            if (id != 0){
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        typedArray.recycle();
        return icons;
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.id_actvityScreenTitles);
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
            case R.id.intelligent:
                startActivity(new Intent(MainActivity.this,IntelligentActivity.class));
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

    @Override
    public void onItemSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (position == POS_HOME){
            HomeFragment homeFragment = new HomeFragment();
            transaction.replace(R.id.frame,homeFragment);
        }

        else if (position == POS_LIST){
            ListFragment listFragment = new ListFragment();
            transaction.replace(R.id.frame,listFragment);
        }

        else if (position == POS_INTELLIGENT){
            startActivity(new Intent(MainActivity.this,IntelligentActivity.class));
        }

        else if (position == POS_TARGET){
            SetTarget setTarget = new SetTarget();
            transaction.replace(R.id.frame,setTarget);
        }

        else if (position == POS_SETTINGS){
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
        }

        else if (position == POS_RATE_APP){
            Toast.makeText(this, "COMING SOON", Toast.LENGTH_SHORT).show();
        }

        else if (position == POS_ABOUT_US){
            Toast.makeText(this, "COMING SOON", Toast.LENGTH_SHORT).show();
        }

        slidingRootNav.closeMenu();
        transaction.addToBackStack(null);
        transaction.commit();
    }
//    private void tellFragments() {
//
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container_fragment);
//        if (!(fragment instanceof OnBackPressed) || !((OnBackPressed) fragment).onBackPressed()) {
//            super.onBackPressed();
//        }
//    }


}
