package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.adapter.PlaceAdapter;
import xit.zubrein.opa.model.ModelSearchPlace;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class PlaceListActivity extends AppCompatActivity {

    String district;
    String stView, budget;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PlaceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        district = getIntent().getStringExtra("district");
        stView = getIntent().getStringExtra("view");
        budget = getIntent().getStringExtra("budget");
        recyclerView = findViewById(R.id.recyclerView);

        if (budget != null) {
            budget_serach_place();
        } else {
            search_place();
        }
    }

    public void search_place() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelSearchPlace>> call = service.search_place(district, stView);
        call.enqueue(new Callback<List<ModelSearchPlace>>() {
            @Override
            public void onResponse(Call<List<ModelSearchPlace>> call, Response<List<ModelSearchPlace>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    List<ModelSearchPlace> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(PlaceListActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new PlaceAdapter(list, PlaceListActivity.this);
                    recyclerView.setAdapter(mAdapter);


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PlaceListActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelSearchPlace>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PlaceListActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void budget_serach_place() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelSearchPlace>> call = service.budget_search(district, budget);
        call.enqueue(new Callback<List<ModelSearchPlace>>() {
            @Override
            public void onResponse(Call<List<ModelSearchPlace>> call, Response<List<ModelSearchPlace>> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    List<ModelSearchPlace> list = response.body();
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(PlaceListActivity.this);
                    recyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new PlaceAdapter(list, PlaceListActivity.this);
                    recyclerView.setAdapter(mAdapter);


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(PlaceListActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelSearchPlace>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PlaceListActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
