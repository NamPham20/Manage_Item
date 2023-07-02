package com.example.manage_item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {


    private TextView tvName;
    private TextView tvId;
    private TextView tvPassword;
    private TextView tvSex;
    private TextView tvAge;
    private TextView tvAddress;
    private TextView tvType;
    private TextView tvContact;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        String strGetId = bundle.getString("transmit_key");
        user = UserDatabase.getInstance(this).userDao().getUserById(strGetId);


        tvName = findViewById(R.id.tvUserName_infor);
        tvId = findViewById(R.id.tvUserNameId_infor);
        tvPassword = findViewById(R.id.tvPassword_infor);
        tvSex = findViewById(R.id.tvSex_infor);
        tvAge = findViewById(R.id.tvAge_infor);
        tvAddress = findViewById(R.id.tvAddress_infor);
        tvType = findViewById(R.id.tvTypeUser_infor);
        tvContact = findViewById(R.id.tvContact_infor);

        tvName.setText(user.getFullName());
        tvId.setText(user.getUserNameId());
        tvPassword.setText(user.getPassword());
        tvSex.setText(user.getSex());
        tvAddress.setText(user.getAddress());
        tvAge.setText(String.valueOf(user.getAge()));
        tvType.setText(user.getTypeUser());
        tvContact.setText(user.getContact());
    }
}