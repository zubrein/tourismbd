package xit.zubrein.opa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import xit.zubrein.opa.HotelActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelHotel;


public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder> {

    List<ModelHotel> list;
    Context c;

    public static class HotelListViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView txt,price,contact,address;
        CardView parent;

        public HotelListViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.image);
            txt = itemView.findViewById(R.id.txt);
            contact = itemView.findViewById(R.id.contact);
            address = itemView.findViewById(R.id.address);
            price = itemView.findViewById(R.id.price);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public HotelListAdapter(List<ModelHotel> list, Context c) {
        this.list = list;
        this.c = c;


    }

    @NonNull
    @Override
    public HotelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item, parent, false);
        HotelListViewHolder evh = new HotelListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull HotelListViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelHotel currentitem = list.get(position);
        holder.txt.setText(currentitem.getHotel_name());
        holder.address.setText(currentitem.getAddress());
        holder.contact.setText(currentitem.getContact_no());
        holder.price.setText("Price : "+currentitem.getPrice());
        Picasso.with(c)
                .load(currentitem.getHotel_image())
                .fit()
                .into(holder.img);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, HotelActivity.class);
                intent.putExtra("id",currentitem.getId());
                c.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
