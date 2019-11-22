package com.example.smarthome.ui.Favourites;

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

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<FavouritesItem> data;

    public FavouritesRecyclerViewAdapter(Context context, List<FavouritesItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.favourites_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.favourites_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked device" + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.favouritesTitle.setText(data.get(position).getFavourite().getName());
        holder.img.setImageResource(data.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout favourites_item;
        private TextView favouritesTitle;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            favourites_item = itemView.findViewById(R.id.favourites_item);
            favouritesTitle = itemView.findViewById(R.id.favouritesTitle);
            img = itemView.findViewById(R.id.favourites_imageView);
        }
    }
}
