package com.example.tasbeehcounter.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.tasbeehcounter.activity.MainActivity;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.model.Counter;
import com.google.android.material.button.MaterialButton;

import static android.content.Context.VIBRATOR_SERVICE;


public class HomeFragment extends Fragment { //implements OnBackPressed{

    public static EditText editText;

    public static TextView textView;
    MaterialButton btnInc,btnDec;
    ImageButton btnSave;
    MaterialButton btnClear;
    Button btnRecord;
    TextView tvGet;
    int time;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public static Vibrator vibrator;
//hjghhjgh
    //gfghfghfgh
    public static int counter = 0;

    AlertDialog.Builder builder;
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
        btnRecord=view.findViewById(R.id.btnRecord);
        tvGet=view.findViewById(R.id.tvGet);

        counter = preferences.getInt("VAL",0);
        textView.setText(String.valueOf(counter));


        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);

        btnInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;
                textView.setText(String.valueOf(counter));
                editor.putInt("VAL",counter);
                editor.commit();

                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(100);
                    }

            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (counter == 0){
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
                }

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



    btnRecord.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {







        }
    });

        return view;
    }
}