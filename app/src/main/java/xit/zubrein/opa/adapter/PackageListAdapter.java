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

import xit.zubrein.opa.PackageDetailsActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.RoomDetailsActivity;
import xit.zubrein.opa.model.ModelPackage;
import xit.zubrein.opa.model.ModelPackage;


public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.RoomListViewHolder> {

    List<ModelPackage> list;
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

    public PackageListAdapter(List<ModelPackage> list, Context c, String hotel_id) {
        this.list = list;
        this.c = c;
        this.hotel_id = hotel_id;


    }

    @NonNull
    @Override
    public RoomListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_item, parent, false);
        RoomListViewHolder evh = new RoomListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelPackage currentitem = list.get(position);
        holder.name.setText(currentitem.getPackage_name());
        holder.price.setText("Price : "+currentitem.getPackage_price());
        Picasso.with(c)
                .load(currentitem.getPackage_image())
                .fit()
                .placeholder(c.getResources().getDrawable(R.drawable.dhanmondilake))
                .into(holder.image);

        holder.book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, PackageDetailsActivity.class);
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
