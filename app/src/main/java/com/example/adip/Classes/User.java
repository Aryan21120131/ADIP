package com.example.adip.Classes;

import android.net.Uri;

public class User {

    String name,
            gender,
            phoneNumber,
            age,
            state,
            city,
            address,
            postalCode,
            password,
            PassportSizePhotoImage,
            DisabilityCertificateImage,
            IncomeCertificateImage,
            IdentityProofImage,
            AddressProofImage,
            Disabilities;
    Uri passportSizePhoto;

    public Uri getPassportSizePhotos() {
        return passportSizePhoto;
    }

    public void setPassportSizePhoto(Uri passportSizePhoto) {
        passportSizePhoto = passportSizePhoto;
    }

    public String getDisabilities() {
        return Disabilities;
    }

    public void setDisabilities(String disabilities) {
        Disabilities = disabilities;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassportSizePhotoImage() {
        return PassportSizePhotoImage;
    }

    public void setPassportSizePhotoImage(String passportSizePhotoImage) {
        PassportSizePhotoImage = passportSizePhotoImage;
    }

    public String getDisabilityCertificateImage() {
        return DisabilityCertificateImage;
    }

    public void setDisabilityCertificateImage(String disabilityCertificateImage) {
        DisabilityCertificateImage = disabilityCertificateImage;
    }

    public String getIncomeCertificateImage() {
        return IncomeCertificateImage;
    }

    public void setIncomeCertificateImage(String incomeCertificateImage) {
        IncomeCertificateImage = incomeCertificateImage;
    }

    public String getIdentityProofImage() {
        return IdentityProofImage;
    }

    public void setIdentityProofImage(String identityProofImage) {
        IdentityProofImage = identityProofImage;
    }

    public String getAddressProofImage() {
        return AddressProofImage;
    }

    public void setAddressProofImage(String addressProofImage) {
        AddressProofImage = addressProofImage;
    }
}
