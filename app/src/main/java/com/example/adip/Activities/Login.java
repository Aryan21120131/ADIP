package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.adip.API.RetrofitClient;
import com.example.adip.Classes.User;
import com.example.adip.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    PinView userPhoneNumberLogin,userPasswordLogin;
    Button LoginButton;
    ImageView LoginLogo;
    TextView PhoneNumberLogin,NameHome;

    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hook();
        LoginLogo.setOnClickListener(view -> {
            if(counter>=5){
                userPhoneNumberLogin.setVisibility(View.INVISIBLE);
                PhoneNumberLogin.setText("ADMIN LOGIN");
            }else{
                counter++;
                int temp=5-counter;
                Toast.makeText(Login.this, "Click on Login icon "+temp+" times to Login as ADMIN !!!", Toast.LENGTH_SHORT).show();
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAsUser();
                if(counter>=5){
                    LoginAsAdmin();
                }else{
                    LoginAsUser();
                }
            }
        });
    }

    private void LoginAsAdmin() {
        if(userPasswordLogin.getText().toString()=="000000"){
            startActivity(new Intent(Login.this,HomePage.class));
        }
    }

    private void LoginAsUser() {
        Call<List<User>> call= RetrofitClient.getService().getUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    for(int i=0;i<response.body().size();i++){
                    if(userPhoneNumberLogin.getText().toString().equals(response.body().get(i).getPhoneNumber())&&
                            userPasswordLogin.getText().toString().equals(response.body().get(i).getPassword())) {
                        User user=response.body().get(i);
                        HomePage.userHome=user;
                        startActivity(new Intent(Login.this, HomePage.class));
                    }
                    }
                }else{
                    Toast.makeText(Login.this, " "+response.message() +" "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable throwable) {

            }
        });
    }

    private void hook() {
        userPhoneNumberLogin=findViewById(R.id.userPhoneNumberLogin);
        userPasswordLogin=findViewById(R.id.userPasswordLogin);
        LoginButton=findViewById(R.id.LoginButton);
        LoginLogo=findViewById(R.id.LoginLogo);
        NameHome=findViewById(R.id.NameHome);
    }
}