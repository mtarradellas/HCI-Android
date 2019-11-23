package com.example.smarthome.ui.RoomView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Device;
import com.example.smarthome.R;
import com.example.smarthome.ui.Favourites.FavouritesItem;
import com.example.smarthome.ui.Favourites.FavouritesRecyclerViewAdapter;
import com.example.smarthome.ui.Home.HomeItem;
import com.example.smarthome.ui.Home.HomeRecyclerViewAdapter;

import java.util.List;

public class RoomRecyclerViewAdapter extends RecyclerView.Adapter<RoomRecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<RoomItem> data;

    public RoomRecyclerViewAdapter(Context context, List<RoomItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(context).inflate(R.layout.room_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(v);

        viewHolder.room_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked device" + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.deviceTitle.setText(data.get(position).getDevice().getName());
        holder.img.setImageResource(data.get(position).getImg());
        if (data.get(position).getDevice().isFav()) {
            holder.heart.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.heart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout room_item;
        private TextView deviceTitle;
        private ImageView img;
        private ImageView heart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            room_item = itemView.findViewById(R.id.room_item);
            deviceTitle = itemView.findViewById(R.id.deviceTitle);
            img = itemView.findViewById(R.id.room_imageView);
            heart = itemView.findViewById(R.id.roomHeart);
        }
    }
}
