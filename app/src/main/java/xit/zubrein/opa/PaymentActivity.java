package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class PaymentActivity extends AppCompatActivity {

    TextView amount;
    LinearLayout msisdn_menu,pin_menu;
    Button next,confirm,close;
    EditText msisdn,pin;

    String selected_date,from;
    String id,hotel_id,user_id;
    SharedPreferences sharedPreferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        id = getIntent().getStringExtra("id");
        hotel_id = getIntent().getStringExtra("hotel_id");
        from = getIntent().getStringExtra("from");
        selected_date = getIntent().getStringExtra("selected_date");

        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id","");
        
        amount = findViewById(R.id.amount);
        msisdn_menu = findViewById(R.id.msisdn_menu);
        pin_menu = findViewById(R.id.pin_menu);
        next = findViewById(R.id.next);
        confirm = findViewById(R.id.confirm);
        close = findViewById(R.id.close);
        msisdn = findViewById(R.id.msisdn);
        pin = findViewById(R.id.pin);
        
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stMsisdn = msisdn.getText().toString();
                if(stMsisdn.startsWith("018") ||stMsisdn.startsWith("017") ||stMsisdn.startsWith("016") ||stMsisdn.startsWith("015")||stMsisdn.startsWith("019")){
                    if(stMsisdn.length() == 11){
                        msisdn_menu.setVisibility(View.GONE);
                        pin_menu.setVisibility(View.VISIBLE);
                    }else{
                        Toast.makeText(PaymentActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(PaymentActivity.this, "Enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pin.getText().toString().equals("12345")){
                    if(from.equals("room")){
                        booking_room();
                    }else{
                        booking_package();
                    }
                }else{
                    Toast.makeText(PaymentActivity.this, "Enter a valid pin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }


    public void booking_room(){


        ApiClient apiClient = new ApiClient();  final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.booking_room(user_id,hotel_id,id,selected_date);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    ModelResponse model = response.body();
                    if(model.getResponse().equals("registered")){
                        CustomToast.showCustomToast(PaymentActivity.this,"Already booked");
                    }else{
                        if(model.getResponse().equals("success")){
                            CustomToastPayment ct = new CustomToastPayment();
                            ct.showCustomToast(PaymentActivity.this,"Booking successful");
                        }else{
                            CustomToast.showCustomToast(PaymentActivity.this,"Booking failed. Try again later");
                        }
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void booking_package(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.booking_package(user_id,hotel_id,id,selected_date);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    ModelResponse model = response.body();
                    if(model.getResponse().equals("registered")){
                        CustomToast.showCustomToast(PaymentActivity.this,"Already booked");
                    }else{
                        if(model.getResponse().equals("success")){
                            CustomToastPayment ct = new CustomToastPayment();
                            ct.showCustomToast(PaymentActivity.this,"Booking successful");
                        }else{
                            CustomToast.showCustomToast(PaymentActivity.this,"Booking failed. Try again late");
                        }
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(PaymentActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class CustomToastPayment {
        public Dialog dialog;
        public void showCustomToast(Activity activity, String messages) {
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(true);
            dialog.setContentView(R.layout.custom_toast);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            TextView message = dialog.findViewById(R.id.dialog_message);
            message.setText(messages);

            FrameLayout mDialogOk = dialog.findViewById(R.id.frmOk);
            mDialogOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            });

            dialog.show();
        }
    }
}