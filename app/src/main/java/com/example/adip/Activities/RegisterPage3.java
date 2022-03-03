package com.example.adip.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Observable;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adip.API.RetrofitClient;
import com.example.adip.API.TempClass;
import com.example.adip.Classes.User;
import com.example.adip.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public class RegisterPage3 extends AppCompatActivity {

    Button Register3Save;
    ImageView PassportSizePhoto,DisabilityCertificate,IncomeCertificate,IdentityProof,AddressProof;

    int SelectedPicture=200;
    Uri[] images=new Uri[5];
    int i;
    ImageView temp;
    String PassportSizePhotoImage,DisabilityCertificateImage,IncomeCertificateImage,IdentityProofImage,AddressProofImage;

    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page3);
        hook();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        PassportSizePhoto.setOnClickListener(view -> {
            temp=PassportSizePhoto;
            i=0;
            imageChooser();
        });
        DisabilityCertificate.setOnClickListener(view -> {
            temp=DisabilityCertificate;
            i=1;
            imageChooser();
        });
        IncomeCertificate.setOnClickListener(view -> {
            temp=IncomeCertificate;
            i=2;
            imageChooser();
        });
        IdentityProof.setOnClickListener(view -> {
            temp=IdentityProof;
            i=3;
            imageChooser();
        });
        AddressProof.setOnClickListener(view -> {
            temp=AddressProof;
            i=4;
            imageChooser();
        });
        Register3Save.setOnClickListener(view -> Save());
    }

    private void Save() {
        if(PassportSizePhotoImage.isEmpty()||
                DisabilityCertificateImage.isEmpty()||
                IncomeCertificateImage.isEmpty()||
                IdentityProofImage.isEmpty()||
                AddressProofImage.isEmpty()){
            Toast.makeText(RegisterPage3.this, "ALL FIELDS REQUIRED !!!", Toast.LENGTH_SHORT).show();
        }else {
//            RegisterPage1.user.setPassportSizePhoto(images[0]);
            HomePage.userHome.setPassportSizePhotoImage(PassportSizePhotoImage);
            HomePage.userHome.setDisabilityCertificateImage(DisabilityCertificateImage);
            HomePage.userHome.setIncomeCertificateImage(IncomeCertificateImage);
            HomePage.userHome.setIdentityProofImage(IdentityProofImage);
            HomePage.userHome.setAddress(AddressProofImage);
            startActivity(new Intent(RegisterPage3.this, DisablityActivity.class));
        }
    }

    private void imageChooser() {
        Intent i=new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,"Select Picture"),SelectedPicture);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==SelectedPicture){
                Uri selectedImageUri=data.getData();
                {
                    if(selectedImageUri!=null){
                        images[i]=selectedImageUri;
                        temp.setAlpha(0.9f);
                        temp.setImageURI(selectedImageUri);
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                            switch (i){
                                case 0:PassportSizePhotoImage=getEncoded64ImageStringFromBitmap(bitmap);
                                    Upload(images[0],HomePage.userHome.getPhoneNumber()+"PassportSizePhoto");
                                    HomePage.userHome.setPassportSizePhotoImage(PassportSizePhotoImage);
                                    break;
                                case 1:DisabilityCertificateImage=getEncoded64ImageStringFromBitmap(bitmap);
                                    Upload(images[1],HomePage.userHome.getPhoneNumber()+"DisabilityCertificate");
                                    HomePage.userHome.setDisabilityCertificateImage(DisabilityCertificateImage);
                                    break;
                                case 2:IncomeCertificateImage=getEncoded64ImageStringFromBitmap(bitmap);
                                    Upload(images[2],HomePage.userHome.getPhoneNumber()+"IncomeCertificate");
                                    HomePage.userHome.setIncomeCertificateImage(IncomeCertificateImage);
                                    break;
                                case 3:IdentityProofImage=getEncoded64ImageStringFromBitmap(bitmap);
                                    Upload(images[3],HomePage.userHome.getPhoneNumber()+"IdentityProof");
                                    HomePage.userHome.setIdentityProofImage(IdentityProofImage);
                                    break;
                                case 4:AddressProofImage=getEncoded64ImageStringFromBitmap(bitmap);
                                    Upload(images[4],HomePage.userHome.getPhoneNumber()+"AddressProof");
                                    HomePage.userHome.setAddressProofImage(AddressProofImage);
                                    break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
//        byte[] byteFormat = stream.toByteArray();
//         get the base 64 string
//        X--------X
//        RequestBody requestBody=RequestBody.create(byteFormat);
//        MultipartBody.Part part=MultipartBody.Part.createFormData("Passport Image","",requestBody);
//        RequestBody someData=RequestBody.create(MediaType.parse("text/plain"),"This is a new IMG");
//        Call call=RetrofitClient.getService().uploadImage(part,someData);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable throwable) {
//
//            }
//        });
//        X--------X
//        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return "";
    }

    private void hook() {
        Register3Save=findViewById(R.id.Register3Save);
        PassportSizePhoto=findViewById(R.id.PassportSizePhoto);
        DisabilityCertificate=findViewById(R.id.DisabilityCertificate);
        IncomeCertificate=findViewById(R.id.IncomeCertificate);
        IdentityProof=findViewById(R.id.IdentityProof);
        AddressProof=findViewById(R.id.AddressProof);
    }

    private void Upload(Uri img,String s){
    if (img != null) {
        StorageReference ref
                = storageReference.child("images/"+ s);
        ref.putFile(img)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(
                                    UploadTask.TaskSnapshot taskSnapshot) {
                                ref.getDownloadUrl();
                                Toast.makeText(RegisterPage3.this,"Image Uploaded!!",Toast.LENGTH_SHORT).show();
                            }
                        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterPage3.this,"Failed " + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
}