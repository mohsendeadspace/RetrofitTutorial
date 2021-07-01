package com.mohsendeadspace.retrofit_java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mohsendeadspace.retrofit_java.Modles.UserModle;
import com.mohsendeadspace.retrofit_java.Retrofit.ApiClient;
import com.mohsendeadspace.retrofit_java.Retrofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    ApiInterface apiInterface;

    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPass)
    EditText edtPass;

    @BindView(R.id.edtPhone)
    EditText edtPhone;

    @BindView(R.id.edtUsername)
    EditText edtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
    }

    public void registerUser(View view){
        Call<UserModle> userRegister = apiInterface.registerUser(edtEmail.getText().toString(),edtUsername.getText().toString(),edtPhone.getText().toString(),edtPass.getText().toString());

        userRegister.enqueue(new Callback<UserModle>() {
            @Override
            public void onResponse(Call<UserModle> call, Response<UserModle> response) {
                if (response.isSuccessful() && response.body() !=null){
                    UserModle userModle = response.body();
                    if (userModle.getSuccess()){
                        Toast.makeText(RegisterActivity.this,"Register Successfull",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Register Not Register",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModle> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Error Connect",Toast.LENGTH_SHORT).show();
            }
        });

    }
}