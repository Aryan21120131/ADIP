package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.adip.API.RetrofitClient;
import com.example.adip.Classes.User;
import com.example.adip.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Eligibility extends AppCompatActivity {

    Button LoginContinue;
    CheckBox checkBoxEligibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eligibility);
        hook();
        LoginContinue.setOnClickListener(view -> {
            if(checkBoxEligibility.isChecked()) {
                PostData();
            }else{
                Toast.makeText(Eligibility.this, "Please select the checkbox!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void PostData() {
        Call<User> call= RetrofitClient.getService().register(HomePage.userHome);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(Eligibility.this,HomePage.class));
                }else {
                    Toast.makeText(Eligibility.this, " "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });
    }

    private void hook() {
        LoginContinue=findViewById(R.id.LoginContinue);
        checkBoxEligibility=findViewById(R.id.checkBoxEligibility);
    }
}