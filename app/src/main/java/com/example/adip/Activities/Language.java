package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.Button;

import com.example.adip.R;

import java.util.Locale;

public class Language extends AppCompatActivity {

    Button hindi,english;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        hook();
        hindi.setOnClickListener(view -> {
            setLocale("hi");
            recreate();
            startActivity(new Intent(Language.this, LoginPage.class));
        });
        english.setOnClickListener(view -> {
            setLocale("en");
            recreate();
            startActivity(new Intent(Language.this, LoginPage.class));
        });
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);

        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
    }

    private void hook() {
        hindi=findViewById(R.id.hindi);
        english=findViewById(R.id.english);
    }
}