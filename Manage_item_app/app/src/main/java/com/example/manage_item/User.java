package com.example.manage_item;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey
    @NonNull
    private String UserNameId;
    private String Password;
    private String FullName;
    private int Age;
    private String Sex;
    private String TypeUser;
    private String Address;
    private String contact;

    public User(String userNameId, String password, String fullName, int age, String sex, String typeUser, String address, String contact) {
        UserNameId = userNameId;
        Password = password;
        FullName = fullName;
        Age = age;
        Sex = sex;
        TypeUser = typeUser;
        Address = address;
        this.contact = contact;
    }

    public User(String strIdUser, String strPasswordUser) {
        this.UserNameId = strIdUser;
        this.Password = strPasswordUser;
    }

    public User() {
    }


    public String getUserNameId() {
        return UserNameId;
    }

    public void setUserNameId( String userNameId) {
        UserNameId = userNameId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getTypeUser() {
        return TypeUser;
    }

    public void setTypeUser(String typeUser) {
        TypeUser = typeUser;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
