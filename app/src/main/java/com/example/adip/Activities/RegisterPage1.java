package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.adip.Classes.User;
import com.example.adip.R;

public class RegisterPage1 extends AppCompatActivity {

    Button Register1Next;
    EditText UserName,UserAge;
    ImageView Female,Male;
    PinView UserPhoneNumber;
    EditText UserAddress,UserState,UserCity;
    PinView UserPostalCode,userPasswordRegister;


    String gender="MALE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page1);
        hook();
        Female.setOnClickListener(view -> {
            gender="FEMALE";
            Female.setAlpha(0.9f);
            Male.setAlpha(0.1f);
        });
        Male.setOnClickListener(view -> {
            gender="MALE";
            Male.setAlpha(0.9f);
            Female.setAlpha(0.1f);
        });
        Register1Next.setOnClickListener(view -> {
            if(UserName.getText().toString().isEmpty()||
                    UserAge.getText().toString().isEmpty()||
                    UserPhoneNumber.getText().toString().isEmpty()||
                    UserAddress.getText().toString().isEmpty()||
                    UserPostalCode.getText().toString().isEmpty()||
                    UserState.getText().toString().isEmpty()||
                    UserCity.getText().toString().isEmpty()||
                    userPasswordRegister.getText().toString().isEmpty()){
                Toast.makeText(RegisterPage1.this, "ALL FIELDS REQUIRED!!!", Toast.LENGTH_SHORT).show();
            }else {
                HomePage.userHome.setName(UserName.getText().toString());
                HomePage.userHome.setAge(UserAge.getText().toString());
                HomePage.userHome.setPhoneNumber(UserPhoneNumber.getText().toString());
                HomePage.userHome.setGender(gender);
                HomePage.userHome.setAddress(UserAddress.getText().toString());
                HomePage.userHome.setPostalCode(UserPostalCode.getText().toString());
                HomePage.userHome.setState(UserState.getText().toString());
                HomePage.userHome.setCity(UserCity.getText().toString());
                HomePage.userHome.setPassword(userPasswordRegister.getText().toString());
                startActivity(new Intent(RegisterPage1.this, RegisterPage3.class));
            }
        });
    }

    private void hook() {
        Register1Next=findViewById(R.id.Register1Next);
        UserName=findViewById(R.id.UserName);
        UserAge=findViewById(R.id.UserAge);
        Female=findViewById(R.id.Female);
        Male=findViewById(R.id.Male);
        UserPhoneNumber=findViewById(R.id.UserPhoneNumber);
        UserState=findViewById(R.id.UserState);
        UserCity=findViewById(R.id.UserCity);
        UserAddress=findViewById(R.id.UserAddress);
        UserPostalCode=findViewById(R.id.UserPostalCode);
        userPasswordRegister=findViewById(R.id.userPasswordRegister);

    }
}