package xit.zubrein.opa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.HotelListAdapter;
import xit.zubrein.opa.adapter.RestaurantListAdapter;
import xit.zubrein.opa.model.ModelRestaurant;
import xit.zubrein.opa.model.ModelRestaurant;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentRestaurants extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RestaurantListAdapter mAdapter;
    String place;

    public FragmentRestaurants(String place) {
        this.place = place;
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_restaurants, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        restaurantList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void restaurantList() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelRestaurant>> call = service.restaurant_info(place);
        call.enqueue(new Callback<List<ModelRestaurant>>() {
            @Override
            public void onResponse(Call<List<ModelRestaurant>> call, Response<List<ModelRestaurant>> response) {
                if (response.isSuccessful()) {
                    List<ModelRestaurant> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new RestaurantListAdapter(list,getContext());
                    recyclerView.setAdapter(mAdapter);


                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelRestaurant>> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
