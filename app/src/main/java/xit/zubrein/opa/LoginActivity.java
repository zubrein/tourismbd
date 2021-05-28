package xit.zubrein.opa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xit.zubrein.opa.model.ModelResponse;
import xit.zubrein.opa.network.ApiClient;
import xit.zubrein.opa.network.ApiInterface;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    EditText etEmail,etPassword;
    Button btnLogin;
    TextView btnregister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("user",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String login = sharedPreferences.getString("login","");
        if(login.equals("yes")){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }else{
            btnLogin = findViewById(R.id.btnLogin);
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            btnregister = findViewById(R.id.btnregister);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    if(email.equals("")){
                        Toast.makeText(LoginActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    }else if (password.equals("")){
                        Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    }else{
                        check_login(email,password);
                    }
                }
            });

            btnregister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });
        }




    }

    public void check_login(String email, String password){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Please wait...");
        progressDialog.show();

        ApiClient apiClient = new ApiClient();
        ApiInterface service = apiClient.createService(ApiInterface.class);
        Call<ModelResponse> call = service.login_check(email,password);
        call.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    ModelResponse model = response.body();
                    if(model.getResponse().equals("yes")){
                        editor.putString("login","yes");
                        editor.putString("user_id",model.getUser_id());
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        finish();
                    }else if(model.getResponse().equals("not_active")){
                        CustomToast.showCustomToast(LoginActivity.this,"Your account is not verified yet. Please check your email.");
                    }else{
                        Toast.makeText(LoginActivity.this, "Check your email and password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Server error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
