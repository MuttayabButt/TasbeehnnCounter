package com.example.tasbeehcounter.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tasbeehcounter.R;
import com.google.android.material.button.MaterialButton;


public class SetTarget extends Fragment {

    EditText target;
    MaterialButton btnDec;
    Button btnSet;
    TextView textSet ;


    SharedPreferences preferences;
    SharedPreferences.Editor editor;


       public SetTarget() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_target, container, false);


        preferences = getActivity().getSharedPreferences("OSHO", Context.MODE_PRIVATE);
        editor = preferences.edit();


        target = view.findViewById(R.id.target);
        btnDec = view.findViewById(R.id.btn_dec);
        btnSet = view.findViewById(R.id.setText);
        textSet = view.findViewById(R.id.set);

        int someint = preferences.getInt("COUNTS", 0);
        textSet.setText(String.valueOf(someint));




        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = target.getText().toString().trim();
                textSet.setText(result);
                Toast.makeText(getActivity(), "setSho", Toast.LENGTH_SHORT).show();

            }
        });


        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if (decrementing > 0) {


                String value = textSet.getText().toString();
                int FinalValue = Integer.parseInt(value);


//                FinalValue = preferences.getInt("COUNTS",0);
//                textSet.setText(String.valueOf(FinalValue));


                if (FinalValue > 0) {

                    FinalValue  --;
                    textSet.setText(String.valueOf(FinalValue));

                    editor.putInt("COUNTS",FinalValue);
                    editor.commit();
                    //}
                }
            }
        });
        return view;
       }
}