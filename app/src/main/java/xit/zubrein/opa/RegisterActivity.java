package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class RegisterActivity extends AppCompatActivity {

    EditText etName,etEmail,etPassword,etConPassword,etMsisdn;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConPassword = findViewById(R.id.etConPassword);
        etMsisdn = findViewById(R.id.etMsisdn);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirm_password = etConPassword.getText().toString();
                String msisdn = etMsisdn.getText().toString();

                if(name.equals("") || email.equals("") || password.equals("") || confirm_password.equals("") || msisdn.equals("")){
                    Toast.makeText(RegisterActivity.this, "Field must not empty", Toast.LENGTH_SHORT).show();
                }else{
                    if(password.equals(confirm_password)){
                        register(name,email,msisdn,password);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });




    }

    public void register(String name,String email,String msisdn, String password){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.registration(name,email,password,msisdn);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    ModelResponse model = response.body();
                    if(model.getResponse().equals("registered")){
                        Toast.makeText(RegisterActivity.this, "Already registered", Toast.LENGTH_SHORT).show();
                    }else{
                        if(model.getResponse().equals("success")){
                            CustomToast ctoast = new CustomToast();
                            ctoast.showCustomToast(RegisterActivity.this,"A verification email has been sent to your email.");
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomToast {
        public  Dialog dialog;
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
                    finish();
                }
            });

            dialog.show();
        }
    }

}


