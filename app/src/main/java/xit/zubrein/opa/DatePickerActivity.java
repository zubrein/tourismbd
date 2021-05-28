package xit.zubrein.opa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class DatePickerActivity extends AppCompatActivity {

    CalendarView calendar;
    Button check;
    String selected_date="",from;
    String id,hotel_id,user_id;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        id = getIntent().getStringExtra("id");
        hotel_id = getIntent().getStringExtra("hotel_id");
        from = getIntent().getStringExtra("from");

        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");

        check= findViewById(R.id.check);
        calendar= findViewById(R.id.calender);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selected_date = dayOfMonth + "-" + (month + 1) + "-" + year;
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected_date.equals("")){
                    Toast.makeText(DatePickerActivity.this, "Please select a date", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(DatePickerActivity.this,PaymentActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("hotel_id",hotel_id);
                    intent.putExtra("from",from);
                    intent.putExtra("selected_date",selected_date);
                    startActivity(intent);
                }
            }
        });

    }

}