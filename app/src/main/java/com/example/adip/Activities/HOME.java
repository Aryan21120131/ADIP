package com.example.adip.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adip.R;

import java.io.File;
import java.io.FileOutputStream;

public class HOME extends AppCompatActivity {

    TextView HomeName,HomePhoneNumber,HomeAge,HomeState,HomeCity,HomeAddress,HomePostalCode;
    ImageView GenderHome;
    Button HomeDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hook();
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        HomeDownload.setOnClickListener(view -> generatePDF());
    }

    private void hook() {
        HomeName=findViewById(R.id.HomeName);
        HomePhoneNumber=findViewById(R.id.HomePhoneNumber);
        HomeAge=findViewById(R.id.HomeAge);
        HomeState=findViewById(R.id.HomeState);
        HomeCity=findViewById(R.id.HomeCity);
        HomeAddress=findViewById(R.id.HomeAddress);
        HomePostalCode=findViewById(R.id.HomePostalCode);
        GenderHome=findViewById(R.id.GenderHome);
        HomeDownload=findViewById(R.id.HomeDownload);
    }

    private void generatePDF() {
        PdfDocument pdfDocument=new PdfDocument();
        Paint myPaint=new Paint();

        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(250,400,1).create();
        PdfDocument.Page page1=pdfDocument.startPage(pageInfo);

        Bitmap bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.logo),100,100,false);
        Bitmap signature=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.temp),75,75,false);
        byte[] bytes= Base64.decode(HomePage.userHome.getPassportSizePhotoImage(),Base64.DEFAULT);
        Bitmap profileImg=Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length),50,50,false);

        Canvas canvas=page1.getCanvas();

        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(40f);
        canvas.drawText("ADIP", pageInfo.getPageWidth()/2,40,myPaint);

        canvas.drawBitmap(profileImg,20 ,50,myPaint);
        canvas.drawBitmap(bitmap,pageInfo.getPageWidth()/2 ,30,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Name: "+HomePage.userHome.getName(), 10,130,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Gender: "+HomePage.userHome.getGender(), 10,140,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Phone Number: "+HomePage.userHome.getPhoneNumber(), 10,150,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Age: "+HomePage.userHome.getAge(), 10,160,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("State: "+HomePage.userHome.getState(), 10,170,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("City: "+HomePage.userHome.getCity(), 10,180,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Address: "+HomePage.userHome.getAddress(), 10,190,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Postal Code: "+HomePage.userHome.getPostalCode(), 10,200,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Disabilities: "+HomePage.userHome.getDisabilities(), 10,210,myPaint);

        canvas.drawBitmap(signature,2.5f*pageInfo.getPageWidth()/4 ,250,myPaint);

        pdfDocument.finishPage(page1);
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/"+Math.random()+".pdf");
        try{
            pdfDocument.writeTo(new FileOutputStream(file));
        }catch (Exception e){
            Toast.makeText(HOME.this, " "+e, Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
    }

}