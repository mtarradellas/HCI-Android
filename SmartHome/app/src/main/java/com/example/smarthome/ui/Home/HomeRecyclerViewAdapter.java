package com.example.smarthome.ui.Home;

import android.content.Context;
import android.content.Intent;
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
import com.example.smarthome.Room;
import com.example.smarthome.RoomViewActivity;

import org.w3c.dom.Text;

import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

    public static final String EXTRA_ROOM = "com.example.smarthome.ui.Home.EXTRA_ROOM";
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
                openRoomViewActivity(v, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    private void openRoomViewActivity(View v, int idx) {
        Room room = data.get(idx).getRoom();

        Intent intent = new Intent(context, RoomViewActivity.class);
        intent.putExtra(EXTRA_ROOM, room);
        context.startActivity(intent);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.textView1.setText(data.get(position).getRoom().getName());
        holder.img.setImageResource(data.get(position).getImg());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout home_item;
        private TextView textView1;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            home_item = itemView.findViewById(R.id.home_item);
            textView1 = itemView.findViewById(R.id.homeItem1);
            img = itemView.findViewById(R.id.imageView);

        }
    }
}
