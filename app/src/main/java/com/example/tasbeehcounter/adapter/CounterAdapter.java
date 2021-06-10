package com.example.tasbeehcounter.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasbeehcounter.R;
import com.example.tasbeehcounter.db.DbHelper;
import com.example.tasbeehcounter.model.Counter;

import java.util.ArrayList;

import static com.example.tasbeehcounter.fragment.HomeFragment.editText;
import static com.example.tasbeehcounter.fragment.HomeFragment.textView;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.CounterVH>{

    ArrayList<Counter> counters;
    Context context;


    public CounterAdapter(ArrayList<Counter> counters, Context context) {
        this.counters = counters;
        this.context = context;
    }

    @NonNull
    @Override
    public CounterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_data, parent,false);
        CounterVH counterVH = new CounterVH(view);

        return counterVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CounterVH holder, int position) {

        Counter counter=counters.get(position);
        holder.tvZikr.setText(counter.getZikr());
        holder.tvAdd.setText(String.valueOf(counter.getCounts()));


        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =new AlertDialog.Builder(context);
                builder.setMessage("r u sure ?");
                builder.setTitle("conformation !!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DbHelper dbHelper = new DbHelper(context);
                        int result = dbHelper.deleteCounter(counter);

                        if (result > 0)
                        {
                            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                            counters.remove(counter);
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                builder.setNegativeButton("No",null);

                builder.show();
            }
        });

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<String>testlist = new ArrayList<String>();
                testlist.add(counters.get(position).getZikr());
                testlist.add((String.valueOf(counter.getCounts())));



                Intent whatsappIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");

//                ArrayList<Counter> testList = new ArrayList<Counter>();
//                testList.add(tvZikr.get(position).getZikr); // Add your text here
//                testList.add(myQuoteList.get(position).getAdd);// Add your text here
//
//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
//                shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, testList);
//                shareIntent.setType("image/*");
//                startActivity(Intent.createChooser(shareIntent, "Share my list to.."));
//

              //  whatsappIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,);
              //  whatsappIntent.putExtra(Intent.EXTRA_TEXT, testlist);
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "data");
               // whatsappIntent.putExtra(Intent.EXTRA_TEXT, "wla");


                try {
                    context.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "WhatsApp have not been installed", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return counters.size();
    }

    class CounterVH extends RecyclerView.ViewHolder{

        public  TextView tvZikr,tvAdd;
        ImageButton ivDel,ivShare;

        public CounterVH(@NonNull View itemView) {
            super(itemView);

        tvZikr=itemView.findViewById(R.id.tvZikr);
        tvAdd=itemView.findViewById(R.id.tvAddition);
        ivDel=itemView.findViewById(R.id.ivDel);
        ivShare=itemView.findViewById(R.id.ivShare);

        }
    }
}
