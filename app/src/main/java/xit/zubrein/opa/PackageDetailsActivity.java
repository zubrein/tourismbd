package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;
import xit.zubrein.opa.model.ModelPackage;
import xit.zubrein.opa.model.ModelPackage;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class PackageDetailsActivity extends AppCompatActivity {

    String id,hotel_id,from;
    ImageView image;
    int num_of_pages;
    TextView description,name;
    Button book_now;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        id = getIntent().getStringExtra("id");
        from = getIntent().getStringExtra("from");
        hotel_id = getIntent().getStringExtra("hotel_id");
        image = findViewById(R.id.image);
        description = findViewById(R.id.description);
        name = findViewById(R.id.name);
        book_now = findViewById(R.id.book_now);
        if(from != null){
            if(from.equals("my_bookings")){
                String code = getIntent().getStringExtra("code");
                book_now.setEnabled(false);
                book_now.setText("Verification code :"+code);
            }
        }

        package_details();

        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PackageDetailsActivity.this,DatePickerActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("hotel_id",hotel_id);
                intent.putExtra("from","package");
                startActivity(intent);
            }
        });

    }

    public void package_details() {

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<List<ModelPackage>> call = service.package_details(id);
        call.enqueue(new Callback<List<ModelPackage>>() {
            @Override
            public void onResponse(Call<List<ModelPackage>> call, Response<List<ModelPackage>> response) {
                if (response.isSuccessful()) {
                    List<ModelPackage> list = response.body();
                    name.setText(list.get(0).getPackage_name());
                    description.setText(list.get(0).getPackage_description());
                    Picasso.with(PackageDetailsActivity.this)
                            .load(list.get(0).getPackage_image())
                            .fit()
                            .placeholder(getResources().getDrawable(R.drawable.dhanmondilake))
                            .into(image);


                } else {
                    Toast.makeText(PackageDetailsActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelPackage>> call, Throwable t) {
                Toast.makeText(PackageDetailsActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
}