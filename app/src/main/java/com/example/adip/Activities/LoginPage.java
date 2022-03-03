package com.example.adip.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.adip.R;

public class LoginPage extends AppCompatActivity {

    Button LoginRegister,LoginLogin;
    Button LoginPageLogo;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        hook();
        LoginRegister.setOnClickListener(view -> startActivity(new Intent(LoginPage.this,RegisterPage1.class)));
        LoginLogin.setOnClickListener(view -> startActivity(new Intent(LoginPage.this,Login.class)));
        LoginPageLogo.setOnClickListener(view -> MSG());
    }

    private void MSG() {
        builder=new AlertDialog.Builder(LoginPage.this);
        builder.setTitle("ADIP");
        builder.setMessage("The government is in a  constant battle to provide aids and appliances for the people who are differently able at a minimum affordable cost. This is made especially important after the enactment of people with disability act 1995 which was sharply enforced in 1996. In many surveys we know that the population of disabled people in India is very high and the survey also shows that many of the disabled people are from a low income family and due to this can't live productive lives. But due to the advancements in the technology in the modern era the disabilities can be overcomed by artificial enhancements such as prosthetic limbs, hearing aids, neural links, crutches, brace, splints, wheelchairs etc. Using such technological marvels we can somewhat cure disabilities although not fully but partially as if a person is deaf then he/she can wear hearing aids so as to continue their day to day life or in the case of an amputee a enhanced bionic leg could prove to be better than their previous leg. But due to the high cost of such prosthetics and aids a large group of people are deprived of such essentials.\n" +
                "The current focus on the differently able people has led to schemes such as ADIP so as to make these essential aids and appliances feasible and reachable to each and everyone in the society. Such schemes can lead to making people with disabilities from liabilities to assets of a country and improve their socio -economic status in the society. This app assists the registration of differently able people in ADIP scheme so as to make aids and appliances feasible for the low income disable people. Which can later make the people self dependable and upright in the society.");
        builder.setCancelable(true);
        builder.setIcon(R.drawable.logo);
        builder.setPositiveButton("OK", (dialogInterface, i) -> alertDialog.cancel());
        alertDialog=builder.create();
        alertDialog.show();
    }

    private void hook() {
        LoginRegister=findViewById(R.id.LoginRegister);
        LoginLogin=findViewById(R.id.LoginLogin);
        LoginPageLogo=findViewById(R.id.LoginPageLogo);
    }
}