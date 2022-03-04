package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adip.API.RetrofitClient;
import com.example.adip.Classes.User;
import com.example.adip.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisablityActivity extends AppCompatActivity {

    ImageView Hearing,Vision,Locomotor,Mental;
    String disabilities="";
    Button DisabilityNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disablity);
        hook();
        Hearing.setOnClickListener(view -> {
            disabilities+="\tHearing";
            Hearing.setAlpha(0.9f);
        });
        Vision.setOnClickListener(view -> {
            disabilities+="\tVision";
            Vision.setAlpha(0.9f);
        });
        Mental.setOnClickListener(view -> {
            disabilities+="\tMental";
            Mental.setAlpha(0.9f);
        });
        Locomotor.setOnClickListener(view -> {
            disabilities+="\tLocomotor";
            Locomotor.setAlpha(0.9f);
        });
        DisabilityNext.setOnClickListener(view -> {
            HomePage.userHome.setDisabilities(disabilities);
            startActivity(new Intent(DisablityActivity.this,Eligibility.class));
        });
    }


    private void hook() {
        Hearing=findViewById(R.id.Hearing);
        Vision=findViewById(R.id.Visual);
        Mental=findViewById(R.id.Mental);
        Locomotor=findViewById(R.id.Locomotor);
        DisabilityNext=findViewById(R.id.DisabilityNext);
    }
}