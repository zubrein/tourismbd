package xit.zubrein.opa.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.PlaceListActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.HotelListAdapter;
import xit.zubrein.opa.adapter.PlaceAdapter;
import xit.zubrein.opa.model.ModelHotel;
import xit.zubrein.opa.model.ModelHotel;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentHotels extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    HotelListAdapter mAdapter;
    String place;


    public FragmentHotels(String place) {
        this.place = place;
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hotel, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        hotelList();

        return view;
    }


    public void hotelList() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelHotel>> call = service.hotel_info(place);
        call.enqueue(new Callback<List<ModelHotel>>() {
            @Override
            public void onResponse(Call<List<ModelHotel>> call, Response<List<ModelHotel>> response) {
                if (response.isSuccessful()) {
                    List<ModelHotel> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new HotelListAdapter(list,getContext());
                    recyclerView.setAdapter(mAdapter);


                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelHotel>> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
