package com.example.manage_item;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        user = (User) getIntent().getExtras().get("transmit_User");


        tvName = findViewById(R.id.tvUserName_pr);
        tvId = findViewById(R.id.tvUserNameId_pr);
        tvPassword = findViewById(R.id.tvPassword_pr);
        tvSex = findViewById(R.id.tvSex_pr);
        tvAge = findViewById(R.id.tvAge_pr);
        tvAddress = findViewById(R.id.tvAddress_pr);
        tvType = findViewById(R.id.tvTypeUser_pr);
        tvContact = findViewById(R.id.tvContact_pr);

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