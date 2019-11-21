package com.example.smarthome.ui.Home;

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

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<HomeItem> data;

    public HomeRecyclerViewAdapter(Context context, List<HomeItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.home_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked room" + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView1.setText(data.get(position).getRoom().getName());
        holder.textView2.setText(data.get(position).getRoom().getMeta().toString());
        holder.img.setImageResource(data.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout home_item;
        private TextView textView1;
        private TextView textView2;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item = itemView.findViewById(R.id.home_item);
            textView1 = itemView.findViewById(R.id.homeItem1);
            textView2 = itemView.findViewById(R.id.homeItem2);
            img = itemView.findViewById(R.id.imageView);

        }
    }
}
