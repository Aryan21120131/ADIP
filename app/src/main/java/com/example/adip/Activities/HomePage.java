package com.example.adip.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.adip.API.RetrofitClient;
import com.example.adip.API.TempClass;
import com.example.adip.Classes.User;
import com.example.adip.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class HomePage extends AppCompatActivity {

//    String sImage;
    ImageView imageView,PasportSizePhotoHome,DisabilityCertificateHome,AddressProofHome,IdentityProofHome,IncomeCertificateHome;
    Button downloadPdf;
    public static User userHome=new User();
    TextView NameHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        hook();
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        for(int i=0;i<5;i++){
            switch (i){
                case 0: imageView=PasportSizePhotoHome;
                    decode64(userHome.getPassportSizePhotoImage());
                    RetrieveImages("PassportSizePhoto",PasportSizePhotoHome);
                    break;
                case 1: imageView=DisabilityCertificateHome;
                    decode64(userHome.getDisabilityCertificateImage());
                    RetrieveImages("DisabilityCertificate",DisabilityCertificateHome);
                    break;
                case 2: imageView=AddressProofHome;
                    decode64(userHome.getAddressProofImage());
                    RetrieveImages("AddressProof",AddressProofHome);
                    break;
                case 3: imageView=IdentityProofHome;
                    decode64(userHome.getIdentityProofImage());
                    RetrieveImages("IdentityProof",IdentityProofHome);
                    break;
                case 4: imageView=IncomeCertificateHome;
                    decode64(userHome.getIncomeCertificateImage());
                    RetrieveImages("IncomeCertificate",IncomeCertificateHome);
                    break;
            }
        }
        downloadPdf.setOnClickListener(view -> generatePDF());
    }

    private void RetrieveImages(String s,ImageView img) {
        StorageReference reference= FirebaseStorage.getInstance().getReference().child("images/5656898565AddressProof.jpeg");
//        try{
//            final File file= File.createTempFile("5656898565AddressProof","jpeg");
//            reference.getFile(file).
//                    addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Bitmap bitmap=BitmapFactory.decodeFile(file.getAbsolutePath());
//                    img.setImageBitmap(bitmap);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(HomePage.this, "Image Loading Failed", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }catch (Exception e){
//            Toast.makeText(HomePage.this, "Failed!!!", Toast.LENGTH_SHORT).show();
//        }
//        NameHome.setText(userHome.getPhoneNumber()+s);
        NameHome.setText(reference.getDownloadUrl().toString());
        Glide.with(HomePage.this ).load(reference).into(img);
    }

    private void generatePDF() {
        PdfDocument pdfDocument=new PdfDocument();
        Paint myPaint=new Paint();

        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(250,400,1).create();
        PdfDocument.Page page1=pdfDocument.startPage(pageInfo);

        Bitmap bitmap=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.logo),100,100,false);
        Bitmap signature=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.temp),75,75,false);
        byte[] bytes= Base64.decode(userHome.getPassportSizePhotoImage(),Base64.DEFAULT);
        Bitmap profileImg=Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length),50,50,false);

        Canvas canvas=page1.getCanvas();

        myPaint.setTextAlign(Paint.Align.CENTER);
        myPaint.setTextSize(40f);
        canvas.drawText("ADIP", pageInfo.getPageWidth()/2,40,myPaint);

        canvas.drawBitmap(profileImg,20 ,50,myPaint);
        canvas.drawBitmap(bitmap,pageInfo.getPageWidth()/2 ,30,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Name: "+userHome.getName(), 10,130,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Gender: "+userHome.getGender(), 10,140,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Phone Number: "+userHome.getPhoneNumber(), 10,150,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Age: "+userHome.getAge(), 10,160,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("State: "+userHome.getState(), 10,170,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("City: "+userHome.getCity(), 10,180,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Address: "+userHome.getAddress(), 10,190,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Postal Code: "+userHome.getPostalCode(), 10,200,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(10f);
        canvas.drawText("Disabilities: "+userHome.getDisabilities(), 10,210,myPaint);

        canvas.drawBitmap(signature,2.5f*pageInfo.getPageWidth()/4 ,250,myPaint);

        pdfDocument.finishPage(page1);
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"/"+Math.random()+".pdf");
        try{
            pdfDocument.writeTo(new FileOutputStream(file));
        }catch (Exception e){
            Toast.makeText(HomePage.this, " "+e, Toast.LENGTH_SHORT).show();
        }

        pdfDocument.close();
    }

    private void decode64(String sImage) {
        try {
//            Toast.makeText(HomePage.this, ""+sImage, Toast.LENGTH_SHORT).show();
//            byte[] bytes = Base64.decode(sImage, Base64.DEFAULT);
//            // Initialize bitmap
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            // set bitmap on imageView
//            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            Toast.makeText(HomePage.this, ""+e, Toast.LENGTH_SHORT).show();
        }
    }


    private void hook() {
        PasportSizePhotoHome=findViewById(R.id.PasportSizePhotoHome);
        DisabilityCertificateHome=findViewById(R.id.DisabilityCertificateHome);
        AddressProofHome=findViewById(R.id.AddressProofHome);
        IdentityProofHome=findViewById(R.id.IdentityProofHome);
        IncomeCertificateHome=findViewById(R.id.IncomeCertificateHome);
        downloadPdf=findViewById(R.id.downloadPdf);
        NameHome=findViewById(R.id.NameHome);
    }
}