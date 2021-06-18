package com.example.tasbeehcounter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasbeehcounter.R;

import java.util.concurrent.TimeUnit;

public class IntelligentActivity extends AppCompatActivity {

    TextView tvTime;
    Button btnStart;
    Button btnStop;
    TextView totalCounts;
    CountDownTimer timerrrr;
    int timezzzzz;
    TextView zikrAver;
    int add = 0;
    private boolean isrunning = false;
    Spinner spinner;
    RadioButton rbBuildIn, rbCustom;
    String[] seconds = { "05", "10", "15", "20", "25","30", "35", "40", "45", "50","55", "60"};
    LinearLayout llm2;
    Button btnSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent);

        tvTime = findViewById(R.id.tvTime);
        btnStart = findViewById(R.id.btnStart);
        totalCounts = findViewById(R.id.tvCounts);
        btnStop = findViewById(R.id.btnStop);
//        zikrAver =findViewById(R.id.Average);
        spinner = findViewById(R.id.spinner);
        rbBuildIn = findViewById(R.id.rbBuildIn);
        rbCustom = findViewById(R.id.rbCustom);

        spinner.setVisibility(View.INVISIBLE);
        llm2=findViewById(R.id.llmTime);
        btnSet=findViewById(R.id.getSet);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, seconds);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(IntelligentActivity.this, "here", Toast.LENGTH_SHORT).show();

                String minutes=spinner.getSelectedItem().toString();

                Toast.makeText(IntelligentActivity.this, minutes, Toast.LENGTH_SHORT).show();

                long mins= TimeUnit.MINUTES.toSeconds(Long.parseLong(minutes));

                Log.d("mins",mins+ "");







            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        rbBuildIn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean Checked = rbBuildIn.isChecked();


                if (Checked){
                    rbBuildIn.setChecked(true);
                    Toast.makeText(IntelligentActivity.this, "rebuildchecked", Toast.LENGTH_SHORT).show();

                    spinner.setVisibility(View.VISIBLE);
                    llm2.setVisibility(View.INVISIBLE);
                    btnSet.setVisibility(View.INVISIBLE);
                    rbCustom.setChecked(false);
                }

            }
        });

        rbCustom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                boolean check = rbCustom.isChecked();

                if (check){

                    rbCustom.setChecked(true);
                    Toast.makeText(IntelligentActivity.this, "rbCustom", Toast.LENGTH_SHORT).show();

                    llm2.setVisibility(View.VISIBLE);
                    btnSet.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.INVISIBLE);


                    rbBuildIn.setChecked(false);
                }
//                else {
//                    rbBuildIn.setChecked(false);
//                    Toast.makeText(IntelligentActivity.this, "Checked", Toast.LENGTH_SHORT).show();
//                }


            }
        });













//        if (rbBuildIn.isChecked()) {
//
//            spinner.setVisibility(View.VISIBLE);
//            rbCustom.setChecked(false);
//            Toast.makeText(this, "BuildIn dy",
//                    Toast.LENGTH_SHORT).show();
//        }
//        if (rbCustom.isChecked()) {
//
//            rbBuildIn.setChecked(false);
//            Toast.makeText(this, "Custom dy",
//                    Toast.LENGTH_SHORT).show();
//
//        }


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                btnStart.setEnabled(false);
//
//                Integer zikr = Integer.valueOf(zikrAver.getText().toString().trim());
//
//
//                timerrrr=  new CountDownTimer(10000, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        tvTime.setText(String.valueOf(timezzzzz));
//                        timezzzzz++;
//
//
//                        isrunning=true;
//
//                        if (timezzzzz == zikr) {
//                            timezzzzz=0;
//                            add++;
//                            totalCounts.setText(add + "");
//
//
//
//
//
//                            /*for (int i = 0; i < zikr; i++) {
//
//
//
//                               // Log.d("TAG", "onTick: " + i);
//                            }*/
//
//                        }
//                    }
//
//
//                    @Override
//                    public void onFinish() {
//
//                        tvTime.setText("Finish");
//                        isrunning=false;
//
//                    }
//                };
//                timerrrr.start();

            }
        });

//        zikrAver.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerrrr.cancel();

                btnStart.setEnabled(true);
            }


        });


//        btnStop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if(timerrrr!=null){
//                    timerrrr.cancel();
//                }
//
//
//                btnStart.setEnabled(true);
//            }
//
//
//        });
    }





//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//
//
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
/*

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                Toast.makeText(IntelligentActivity.this, "1", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(IntelligentActivity.this, "2", Toast.LENGTH_SHORT).show();

                break;
            case 2:
                Toast.makeText(IntelligentActivity.this, "3", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(IntelligentActivity.this, "4", Toast.LENGTH_SHORT).show();

                break;
            case 4:
                Toast.makeText(IntelligentActivity.this, "5", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(IntelligentActivity.this, "6", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                Toast.makeText(IntelligentActivity.this, "7", Toast.LENGTH_SHORT).show();

                break;
            case 7:
                Toast.makeText(IntelligentActivity.this, "8", Toast.LENGTH_SHORT).show();
                break;
            case 8:
                Toast.makeText(IntelligentActivity.this, "9", Toast.LENGTH_SHORT).show();

                break;
            case 9:
                Toast.makeText(IntelligentActivity.this, "10", Toast.LENGTH_SHORT).show();
                break;
            case 10:
                Toast.makeText(IntelligentActivity.this, "11", Toast.LENGTH_SHORT).show();

                break;
            case 11:
                Toast.makeText(IntelligentActivity.this, "12", Toast.LENGTH_SHORT).show();
                break;
        }
    }
*/


}