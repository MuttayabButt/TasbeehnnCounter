package com.example.tasbeehcounter.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasbeehcounter.R;
import com.example.tasbeehcounter.activity.IntelligentActivity;
import com.example.tasbeehcounter.activity.MainActivity;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.model.Counter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;


public class HomeFragment extends Fragment { //implements OnBackPressed{

    public static EditText editText;

    public static TextView textView;
    MaterialButton btnInc,btnDec;
    ImageButton btnSave;
    MaterialButton btnClear;
    int time;
    Button record;
    TextView tvv;

    ArrayList<Integer> arrayList=new ArrayList<>();
    CountDownTimer timer;
    int timezzzz = 0;
    Button btnStop;
    int average;



    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static Vibrator vibrator;
 
    public static int counter = 0;

    AlertDialog.Builder builder;
    private int sum;
    private int get=0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        preferences = getActivity().getSharedPreferences("VALUE", Context.MODE_PRIVATE);
        editor = preferences.edit();

        editText = view.findViewById(R.id.eT);

        builder = new AlertDialog.Builder(getActivity());

        textView = view.findViewById(R.id.tvCounts);
        btnInc = view.findViewById(R.id.btnInc);
        btnDec = view.findViewById(R.id.btnDec);
        btnSave = view.findViewById(R.id.imageButton1);
        btnClear=view.findViewById(R.id.btnClear);
        btnStop=view.findViewById(R.id.stop);
        record=view.findViewById(R.id.record);
        tvv=view.findViewById(R.id.tvTv);
//        counter = preferences.getInt("VAL",0);
//        textView.setText(String.valueOf(counter));
//        average = preferences.getInt("averge",2);
//        tvPut.setText(String.valueOf(average));



        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                textView.setText(String.valueOf(counter));
                editor.putInt("VAL",counter);
                editor.commit();


            }
                });



        if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(100);
                    }


        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                record.setEnabled(true);

                timer.cancel();

                arrayList.add(timezzzz-1);

                int gett = Integer.parseInt(record.getText().toString().trim());



//            Q Q   Q?
//                        ans=enter,not,give,not given
//                        ans:enter
//                               ans[]= ans.spir(",");
//                1,2,3,4



            }
        });


            btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateAv();

                Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),IntelligentActivity.class);
                intent.putExtra("EdiTtEXTvALUE", tvv.getText().toString());
                startActivity(intent);

         /*       if (counter == 0){
                    counter++;
                }
                counter--;
                textView.setText(String.valueOf(counter));
                editor.putInt("VAL",counter);
                editor.commit();


                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(100);
                }*/

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setMessage("r u sure to save");
                builder.setTitle(" Alert");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String zikr = editText.getText().toString().trim();
                        if (zikr.isEmpty()){
                            Toast.makeText(getActivity() , "Plz Enter zikr to save", Toast.LENGTH_SHORT).show();
                        }

                        String counts = textView.getText().toString().trim();
                        if (counts.isEmpty()){

                            Toast.makeText(getActivity(), "Enter counts to save", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Counter counter = new Counter(zikr,Integer.parseInt(counts));

                        DbHelper dbHelper = new DbHelper(getActivity());
                        long result = dbHelper.addCounter(counter);

                        if (result > 0){
                            Toast.makeText(getActivity(), "saved in list", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder.setMessage("R u sure to restart your counter?");
                builder.setTitle("Alert Message");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        if (counter > 0) {
                            counter = 0;
                            textView.setText(String.valueOf(counter));

                            editor.remove("VAL");
                            boolean success = editor.commit();
                            if (success) {
                                // do something
                                counter = 0 ;
                                Toast.makeText(getActivity(), "gone", Toast.LENGTH_SHORT).show();
                            }
                            editor.apply();
                        }
                        Toast.makeText(getActivity(), "Counter Restart", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                builder.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                record.setEnabled(false);
                timer=  new CountDownTimer(100000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        record.setText(String.valueOf(timezzzz));
                        timezzzz++;

                    }
                    @Override
                    public void onFinish() {

                    }
                };
                timer.start();


            }
        });


        return view;
    }

    public void calculateAv(){

        for (int i=0;i<arrayList.size();i++){


            Log.d("my  value"+i," is "+arrayList.get(i));
            get = get + arrayList.get(i);

        }
        average=get/arrayList.size();
        Log.d("my average",average+"");
        tvv.setText(String.valueOf(average));






    }



}





//    public static void saveArray(Context ctx, int[] array) {
//        String strArr = "";
//        for (int i=0; i<array.length; i++) {
//            strArr += array[i] + ",";
//        }
//        strArr = strArr.substring(0, strArr.length() -1); // get rid of last comma
//
//        SharedPreferences.Editor e = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
//        e.putString("MY_ARRAY", strArr);
//        e.commit();
//    }
//    public static int[] getArray(Context ctx) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
//        String str = prefs.getString("MY_ARRAY", null);
//        String[] strArr = str.split(",");
//        int[] array = new int[strArr.length];
//        for (int i=0; i<strArr.length; i++) {
//            array[i] = new Integer(strArr[i]);
//        }
//        return array;
//    }

//    public double incassoMargherita()
//    {
//        double sum = 0;
//        for(int i = 0; i < arrayList.size(); i++)
//        {
//            sum = sum + arrayList.get(i);
//        }
//        return sum;
//    }
