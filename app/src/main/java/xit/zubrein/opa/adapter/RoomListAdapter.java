package xit.zubrein.opa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import xit.zubrein.opa.R;
import xit.zubrein.opa.RoomDetailsActivity;
import xit.zubrein.opa.model.ModelRoom;


public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.RoomListViewHolder> {

    List<ModelRoom> list;
    Context c;

    String hotel_id;

    public static class RoomListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,air_condition,num_of_room,price;
        Button book_now;
        CardView parent;

        public RoomListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            num_of_room = itemView.findViewById(R.id.num_of_room);
            air_condition = itemView.findViewById(R.id.air_condition);
            book_now = itemView.findViewById(R.id.book_now);
            price = itemView.findViewById(R.id.price);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public RoomListAdapter(List<ModelRoom> list, Context c, String hotel_id) {
        this.list = list;
        this.c = c;
        this.hotel_id = hotel_id;


    }

    @NonNull
    @Override
    public RoomListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        RoomListViewHolder evh = new RoomListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelRoom currentitem = list.get(position);
        holder.name.setText(currentitem.getFlat_name());
        if(currentitem.getFlat_ac().equals("0")){
            holder.air_condition.setText("Non AC room");
        }else{
            holder.air_condition.setText("Air conditioned");
        }

        holder.num_of_room.setText(currentitem.getFlat_total_room()+" Rooms");
        holder.price.setText("Price : "+currentitem.getFlat_price());
        if(currentitem.getImages().size() != 0){
            Picasso.with(c)
                    .load(currentitem.getImages().get(0))
                    .fit()
                    .placeholder(c.getResources().getDrawable(R.drawable.dhanmondilake))
                    .into(holder.image);
        }

        holder.book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, RoomDetailsActivity.class);
                intent.putExtra("id",currentitem.getId());
                intent.putExtra("hotel_id",hotel_id);
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
