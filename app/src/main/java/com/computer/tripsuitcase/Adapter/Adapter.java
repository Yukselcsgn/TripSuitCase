package com.computer.tripsuitcase.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.computer.tripsuitcase.CheckList;
import com.computer.tripsuitcase.Constants.MyConstants;
import com.computer.tripsuitcase.R;

import java.util.List;


//FRONT END PROBLEMLERİ
public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>{

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;
    Activity activity;
    private Context context;

    public Adapter(Context context, List<String> titles, List<Integer> images, Activity activity) {
        this.titles = titles;
        this.images = images;
        this.activity = activity;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("ADAPTER-onCreateViewHolder","GİRİŞ");
        View view = inflater.inflate(R.layout.main_item, parent,false);
        Log.e("ADAPTER-onCreateViewHolder","ÇIKIŞ");
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.e("ADAPTER-onBindViewHolder","GİRİŞ");
        holder.title.setText(titles.get(position));
        holder.img.setImageResource(images.get(position));
        holder.linearLayout.setAlpha(0.8F);
        Log.e("ADAPTER-onBindViewHolder","ORTA");

        holder.linearLayout.setOnClickListener(view -> {
            //Toast.makeText(activity, "Clicked on Card", Toast.LENGTH_SHORT).show( );
            Intent intent = new Intent( view.getContext(), CheckList.class );
            intent.putExtra(MyConstants.HEADER_SMALL,titles.get(position));
            if (MyConstants.MY_SELECTIONS.equals(titles.get(position))){
                intent.putExtra(MyConstants.SHOW_SMALL,MyConstants.FALSE_STRING);
            }else {
                intent.putExtra(MyConstants.SHOW_SMALL,MyConstants.TRUE_STRING);
            }
            view.getContext().startActivity(intent);
        });
        Log.e("ADAPTER-onBindViewHolder","ÇIKIŞ");
    }

    @Override
    public int getItemCount() {
        Log.e("ADAPTER-getItemCount","ÇALIŞTI");
        return titles.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView img;
        LinearLayout linearLayout;

        @SuppressLint("CutPasteId")
        public myViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.e("ADAPTER-myViewHolder","ÇALIŞTI2");
            title = itemView.findViewById(R.id.title);
            Log.e("ADAPTER-myViewHolder","ÇALIŞTI3");
            img = itemView.findViewById(R.id.img);
            Log.e("ADAPTER-myViewHolder","ÇALIŞTI4");
            linearLayout = itemView.findViewById(R.id.linearLayout);
            Log.e("ADAPTER-myViewHolder","ÇALIŞTI5");

        }
    }
}
