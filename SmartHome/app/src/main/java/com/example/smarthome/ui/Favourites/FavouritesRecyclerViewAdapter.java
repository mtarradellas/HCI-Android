package com.example.smarthome.ui.Favourites;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smarthome.Device;
import com.example.smarthome.ui.DevicesViews.LampActivity;
import com.example.smarthome.R;

import java.util.List;

public class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.MyViewHolder> {

    public static final String EXTRA_DEVICE = "com.example.smarthome.ui.Favourites.EXTRA_DEVICE";
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
                openDeviceViewActivity(viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    private void openDeviceViewActivity(int idx) {
        Device device = data.get(idx).getFavourite();
        // TODO switch device type


        Intent intent = new Intent(context, LampActivity.class);
        intent.putExtra(EXTRA_DEVICE, (Parcelable) device);
        context.startActivity(intent);
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
