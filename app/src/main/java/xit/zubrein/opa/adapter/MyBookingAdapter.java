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

import java.util.List;

import xit.zubrein.opa.R;
import xit.zubrein.opa.RoomDetailsActivity;
import xit.zubrein.opa.model.ModelRoom;


public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.MyBookingListViewHolder> {

    List<ModelRoom> list;
    Context c;

    public static class MyBookingListViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,air_condition,num_of_room,price,date;
        Button book_now;
        CardView parent;

        public MyBookingListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            num_of_room = itemView.findViewById(R.id.num_of_room);
            air_condition = itemView.findViewById(R.id.air_condition);
            book_now = itemView.findViewById(R.id.book_now);
            price = itemView.findViewById(R.id.price);
            parent = itemView.findViewById(R.id.parent);
            date = itemView.findViewById(R.id.date);
        }
    }

    public MyBookingAdapter(List<ModelRoom> list, Context c) {
        this.list = list;
        this.c = c;


    }

    @NonNull
    @Override
    public MyBookingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybooking_item, parent, false);
        MyBookingListViewHolder evh = new MyBookingListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingListViewHolder holder, int position) {
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
        holder.date.setText("Date : "+currentitem.getDate());
        if(currentitem.getImages() != null){
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
                intent.putExtra("hotel_id",currentitem.getHotel_id());
                intent.putExtra("from","my_bookings");
                intent.putExtra("code",currentitem.getCode());
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
