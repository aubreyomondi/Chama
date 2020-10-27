package com.mobilefintech16.chama;

public class UserHelperClass {
    String fullName, username, nationalId, phoneNo, email, password;

    // include empty constructor to avoid errors in firebase


    public UserHelperClass() {
    }

    public UserHelperClass(String fullName, String username, String nationalId, String phoneNo, String email, String password) {
        this.fullName = fullName;
        this.username = username;
        this.nationalId = nationalId;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
