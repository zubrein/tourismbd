package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;
import xit.zubrein.opa.adapter.RoomListAdapter;
import xit.zubrein.opa.model.ModelRoom;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class RoomDetailsActivity extends AppCompatActivity {

    String id,hotel_id,from;
    FlipperLayout flipperLayout;
    int num_of_pages;
    TextView description,name;
    Button book_now;
    TextView bathroom,ac,tv,balcony,wifi,lift,price,sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        id = getIntent().getStringExtra("id");
        from = getIntent().getStringExtra("from");
        hotel_id = getIntent().getStringExtra("hotel_id");
        flipperLayout = findViewById(R.id.flipper_layout);

        description = findViewById(R.id.description);
        bathroom = findViewById(R.id.bathroom);
        ac = findViewById(R.id.ac);
        tv = findViewById(R.id.tv);
        balcony = findViewById(R.id.balcony);
        wifi = findViewById(R.id.wifi);
        lift = findViewById(R.id.lift);
        price = findViewById(R.id.price);
        sp = findViewById(R.id.sp);

        name = findViewById(R.id.name);
        book_now = findViewById(R.id.book_now);
        if(from != null){
            if(from.equals("my_bookings")){

                String code = getIntent().getStringExtra("code");
                book_now.setEnabled(false);
                book_now.setText("Verification code :"+code);
            }
        }

        room_details();

        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomDetailsActivity.this,DatePickerActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("hotel_id",hotel_id);
                intent.putExtra("from","room");
                startActivity(intent);
            }
        });

    }

    public void room_details() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelRoom>> call = service.room_details(id);
        call.enqueue(new Callback<List<ModelRoom>>() {
            @Override
            public void onResponse(Call<List<ModelRoom>> call, Response<List<ModelRoom>> response) {
                if (response.isSuccessful()) {
                    List<ModelRoom> list = response.body();
                    name.setText(list.get(0).getFlat_name());
                    description.setText(list.get(0).getDescription());
                    bathroom.setText(list.get(0).getTotal_bath()+" Bathrooms");
                    price.setText(list.get(0).getFlat_price()+" BDT");
                    if(list.get(0).getFlat_ac().equals("0")){
                        ac.setText("Non AC");
                    }else{
                        ac.setText("Air Conditioned");
                    }
                    if(list.get(0).getBalcony().equals("0")){
                        balcony.setText("NO");
                    }else{
                        balcony.setText("YES");
                    }
                    if(list.get(0).getTv().equals("0")){
                        tv.setText("NO");
                    }else{
                        tv.setText("YES");
                    }
                    if(list.get(0).getWifi().equals("0")){
                        wifi.setText("NO");
                    }else{
                        wifi.setText("YES");
                    }
                    if(list.get(0).getLift().equals("0")){
                        lift.setText("NO");
                    }else{
                        lift.setText("YES");
                    }
                    if(list.get(0).getSwimmingpool().equals("0")){
                        sp.setText("NO");
                    }else{
                        sp.setText("YES");
                    }
                    if(list.get(0).getImages()!= null) {
                        num_of_pages = list.get(0).getImages().size();

                        for (int j = 0; j < num_of_pages; j++) {
                            FlipperView view1 = new FlipperView(RoomDetailsActivity.this);
                            view1.setImageUrl(list.get(0).getImages().get(j));
                            flipperLayout.setScrollTimeInSec(100);
                            flipperLayout.addFlipperView(view1);
                        }

                    }else{
                        Toast.makeText(RoomDetailsActivity.this, "No image found", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(RoomDetailsActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelRoom>> call, Throwable t) {
                Toast.makeText(RoomDetailsActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}