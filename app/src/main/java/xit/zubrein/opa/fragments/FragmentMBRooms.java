package xit.zubrein.opa.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
import xit.zubrein.opa.MyBookingsActivity;
import xit.zubrein.opa.R;
import xit.zubrein.opa.adapter.MyBookingAdapter;
import xit.zubrein.opa.adapter.RoomListAdapter;
import xit.zubrein.opa.model.ModelRoom;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class FragmentMBRooms extends Fragment {

    String user_id;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyBookingAdapter mAdapter;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hotel_room, container, false);

        sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");

        recyclerView = view.findViewById(R.id.recyclerView);

        hotelRoomList();


        return view;
    }

    public void hotelRoomList() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelRoom>> call = service.my_bookings(user_id);
        call.enqueue(new Callback<List<ModelRoom>>() {
            @Override
            public void onResponse(Call<List<ModelRoom>> call, Response<List<ModelRoom>> response) {
                if (response.isSuccessful()) {
                    List<ModelRoom> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new MyBookingAdapter(list,getContext());
                    recyclerView.setAdapter(mAdapter);


                } else {
                    Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelRoom>> call, Throwable t) {
                Toast.makeText(getContext(), "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
