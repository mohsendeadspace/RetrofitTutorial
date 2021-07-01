package com.mohsendeadspace.retrofit_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


public class LoginActivity extends AppCompatActivity {

    ApiInterface apiInterface;


    @BindView(R.id.edtEmail)
    EditText edtEmail;

    @BindView(R.id.edtPass)
    EditText edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);



    }

    public void logInUser(View view){

        Call<UserModle> userModleCall = apiInterface.loginUser(edtEmail.getText().toString(),
                edtPass.getText().toString());

        userModleCall.enqueue(new Callback<UserModle>() {
            @Override
            public void onResponse(Call<UserModle> call, Response<UserModle> response) {

                if (response.body() != null){
                    UserModle userModle = response.body();
                   if (userModle.getSuccess()){
                       Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(LoginActivity.this,
                               "User Not Found"+userModle.getMessage(),Toast.LENGTH_SHORT).show();

                   }
                }
            }

            @Override
            public void onFailure(Call<UserModle> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Error Connect",Toast.LENGTH_SHORT).show();
                Log.i("Log",call+"");
            }
        });
    }

    public void registerUser(View view){
        Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}