package com.example.tasbeehcounter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasbeehcounter.R;

public class IntelligentActivity extends AppCompatActivity {

    TextView tvTime;
    Button btnStart;
    Button btnStop;
    TextView totalCounts;
    CountDownTimer timerrrr;
    int timezzzzz;
    TextView zikrAver;
    int add=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent);

        tvTime=findViewById(R.id.tvTime);
        btnStart=findViewById(R.id.btnStart);
        totalCounts=findViewById(R.id.tvCounts);
        btnStop=findViewById(R.id.btnStop);
        zikrAver =findViewById(R.id.Average);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                btnStart.setEnabled(false);
                Integer zikr = Integer.valueOf(zikrAver.getText().toString().trim());

                for (int i =0 ; i<zikr ; i++) {
                    Log.d("TAG", "onClick: "+i);
                    totalCounts.setText(String.valueOf(i));
                    timerrrr=  new CountDownTimer(i, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            tvTime.setText(String.valueOf(timezzzzz));
                            timezzzzz++;


                            if ( timezzzzz == zikr) {
                                add++;
                                totalCounts.setText(add+"");

                            }else {
                                Toast.makeText(IntelligentActivity.this, "ww",
                                        Toast.LENGTH_SHORT).show();
                            }
                      /*  for (int i =0 ; i<zikr ; i++) {
                            Log.d("TAG", "onClick: "+i);
                            totalCounts.setText(String.valueOf(i));

                        }*/



                        }
                        @Override
                        public void onFinish() {

                            tvTime.setText("Finish");
                    /*    Integer abc = Integer.valueOf(zikrAver.getText().toString());
                        if (tvTime.equals(abc)){
                            Toast.makeText(IntelligentActivity.this, "xcxc",
                                    Toast.LENGTH_SHORT).show();
                        }*/
                        }
                    };
                    timerrrr.start();

                }
                //Toast.makeText(IntelligentActivity.this, "ewrer", Toast.LENGTH_SHORT).show();





            }
        });

        zikrAver.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));



        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if (timezzzzz > 0){
//
//                    timerrrr.cancel();
//                    tvTime.setText("0");
//                }


                if(timerrrr!=null){
                    timerrrr.cancel();
                }


                btnStart.setEnabled(true);




            }
        });
    }
}