package com.example.smarthome.ui.Routines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.R;
import com.example.smarthome.ui.Home.HomeItem;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.List;

public class RoutinesRecyclerViewAdapter extends RecyclerView.Adapter<RoutinesRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<RoutinesItem> data;

    public RoutinesRecyclerViewAdapter(Context context, List<RoutinesItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.routines_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.routines_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Routine " + data.get(viewHolder.getAdapterPosition()).getRoutine().getName() + " executed", Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.routinesTitle.setText(data.get(position).getRoutine().getName());
        holder.img.setImageResource(data.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout routines_item;
        private TextView routinesTitle;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            routines_item = itemView.findViewById(R.id.routines_item);
            routinesTitle = itemView.findViewById(R.id.routinesTitle);
            img = itemView.findViewById(R.id.routines_imageView);

        }
    }
}
