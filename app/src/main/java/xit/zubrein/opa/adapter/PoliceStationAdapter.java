package xit.zubrein.opa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xit.zubrein.opa.R;
import xit.zubrein.opa.model.ModelPoliceStation;


public class PoliceStationAdapter extends RecyclerView.Adapter<PoliceStationAdapter.PoliceStationViewHolder> {

    List<ModelPoliceStation> list;
    Context c;

    public static class PoliceStationViewHolder extends RecyclerView.ViewHolder {
        public TextView name,address,contact,position;
        CardView parent;

        public PoliceStationViewHolder(@NonNull View itemView) {
            super(itemView);

            contact = itemView.findViewById(R.id.contact);
            address = itemView.findViewById(R.id.address);
            name = itemView.findViewById(R.id.name);
            parent = itemView.findViewById(R.id.parent);
            position = itemView.findViewById(R.id.position);
        }
    }

    public PoliceStationAdapter(List<ModelPoliceStation> list, Context c) {
        this.list = list;
        this.c = c;


    }

    @NonNull
    @Override
    public PoliceStationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item, parent, false);
        PoliceStationViewHolder evh = new PoliceStationViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PoliceStationViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        final ModelPoliceStation currentitem = list.get(position);
        holder.position.setText(String.valueOf(position+1));
        holder.name.setText(currentitem.getPolice_station_name());
        holder.address.setText(currentitem.getAddress());
        holder.contact.setText(currentitem.getContact_no());

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

}
