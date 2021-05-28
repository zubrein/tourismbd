package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullImageActivity extends AppCompatActivity {

    ImageView img;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        url = getIntent().getStringExtra("url");
        img = findViewById(R.id.img);
        Picasso.with(FullImageActivity.this).load(url).into(img);


    }
}
