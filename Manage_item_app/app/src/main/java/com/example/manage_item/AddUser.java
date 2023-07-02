package com.example.manage_item;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manage_item.Fragment.UserlistFargment;

import java.util.List;

public class AddUser extends AppCompatActivity {

    private EditText edtUserNameId;
    private EditText edtPassword;
    private EditText edtAddress;
    private EditText edtSex;
    private EditText edtFullName;
    private EditText edtAge;
    private EditText edtTypeUser;

    private EditText edtContact;
    private Button btnAddUser;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        edtUserNameId =(EditText) findViewById(R.id.edtUserNameId_add);
        edtPassword =(EditText) findViewById(R.id.edtPassword_add);
        edtAddress =(EditText) findViewById(R.id.edtAddress_add);
        edtFullName =(EditText) findViewById(R.id.edtFullName_add);
        edtAge =(EditText) findViewById(R.id.edtAge_add);
        edtSex = findViewById(R.id.edtSex_add);
        edtTypeUser =findViewById(R.id.edtTypeUser_Add);
        btnAddUser = findViewById(R.id.btnAddUser_Add);
        edtContact = findViewById(R.id.edtContact_add);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addUser();
            }

        });

    }

    private void addUser(){
        String str_userNameId = edtUserNameId.getText().toString().trim();
        String str_password = edtPassword.getText().toString().trim();
        String str_fullName = edtFullName.getText().toString().trim();
        String str_address = edtAddress.getText().toString().trim();
        String str_Age = edtAge.getText().toString().trim();
        int iAge = Integer.parseInt(str_Age);
        String str_TypeUser =edtTypeUser.getText().toString().trim();
        String str_Sex = edtSex.getText().toString().trim();
        String str_Contact = edtContact.getText().toString().trim();


        if(TextUtils.isEmpty(str_userNameId)||TextUtils.isEmpty(str_address)||TextUtils.isEmpty(str_fullName)
                ||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(str_Age)){
            Toast.makeText(this,"There's still space left",Toast.LENGTH_LONG).show();
            return;
        }

        User user    = new User(str_userNameId ,str_password,str_fullName,iAge,str_Sex,str_TypeUser,str_address,str_Contact);
        if (isAccountExit(user)){
            Toast.makeText(this,"Account Exits",Toast.LENGTH_LONG).show();
            return;
        }

        UserDatabase.getInstance(this).userDao().insertUser(user);
        Toast.makeText(this,"AAD ACCOUNT SUCCESSFULLY!",Toast.LENGTH_LONG).show();
        Intent intentBack = new Intent();
        intentBack.putExtra("key2",user);
        setResult(RESULT_OK,intentBack);
        finish();

    }

    private boolean isAccountExit(User user) {
        List<User> list= UserDatabase.getInstance(this).userDao().checkUser(user.getUserNameId());
        return list != null && !list.isEmpty();
    }
}