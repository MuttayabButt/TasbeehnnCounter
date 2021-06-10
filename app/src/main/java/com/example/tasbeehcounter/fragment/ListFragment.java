package com.example.tasbeehcounter.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tasbeehcounter.R;
import com.example.tasbeehcounter.adapter.CounterAdapter;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.model.Counter;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Counter> counters;
    //adapter
    CounterAdapter counterAdapter;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_list, container, false);

         recyclerView=view.findViewById(R.id.recycler);

         getAllCounters();

      return view;
    }

    private void getAllCounters() {

        DbHelper dbHelper = new DbHelper(getActivity());

        counters = dbHelper.grabAllCounter();

        counterAdapter =new CounterAdapter(counters,getActivity());
        recyclerView.setAdapter(counterAdapter);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(llm);
 

    }
}