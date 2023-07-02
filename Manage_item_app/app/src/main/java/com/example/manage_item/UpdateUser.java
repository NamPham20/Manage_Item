package com.example.manage_item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUser extends AppCompatActivity {

    private EditText edtUserNameId;
    private EditText edtPassword;
    private EditText edtAddress;
    private EditText edtSex;
    private EditText edtFullName;
    private EditText edtAge;
    private EditText edtTypeUser;

    private EditText edtContact;
    private Button btnUpdateUser;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        edtUserNameId =(EditText) findViewById(R.id.edtUserNameId_update);
        edtPassword =(EditText) findViewById(R.id.edtPassword_update);
        edtAddress =(EditText) findViewById(R.id.edtAddress_update);
        edtFullName =(EditText) findViewById(R.id.edtFullName_update);
        edtAge =(EditText) findViewById(R.id.edtAge_update);
        edtSex = findViewById(R.id.edtSex_update);
        edtTypeUser =findViewById(R.id.edtTypeUser_update);
        btnUpdateUser = findViewById(R.id.btnUpdateUser_update);
        edtContact = findViewById(R.id.edtContact_update);

        mUser = (User) getIntent().getExtras().get("key_id");
        if (mUser != null){
            edtUserNameId.setText(mUser.getUserNameId());
            edtPassword.setText(mUser.getPassword());
            edtFullName.setText(mUser.getFullName());
            edtAddress.setText(mUser.getAddress());
            edtAge.setText(String.valueOf(mUser.getAge()));
            edtTypeUser.setText(mUser.getTypeUser());
            edtContact.setText(mUser.getContact());
            edtSex.setText(mUser.getSex());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String str_userNameId = edtUserNameId.getText().toString().trim();
        String str_password = edtPassword.getText().toString().trim();
        String str_fullName = edtFullName.getText().toString().trim();
        String str_address = edtAddress.getText().toString().trim();
        String str_Age = edtAge.getText().toString().trim();
        String str_TypeUser =edtTypeUser.getText().toString().trim();
        String str_Sex = edtSex.getText().toString().trim();
        String str_Contact = edtContact.getText().toString().trim();


        if(TextUtils.isEmpty(str_userNameId)||TextUtils.isEmpty(str_address)||TextUtils.isEmpty(str_fullName)
                ||TextUtils.isEmpty(str_password)||TextUtils.isEmpty(str_Age)){
            Toast.makeText(this,"There's still space left",Toast.LENGTH_LONG).show();
            return;
        }
        int iAge = Integer.parseInt(str_Age);

        if(!str_TypeUser.equals("Manager") && !str_TypeUser.equals("Employee") ){
            Toast.makeText(this,"User Type Must be \"Manager\" or \"Employee\"",Toast.LENGTH_LONG).show();
            return;
        }

        mUser.setUserNameId(str_userNameId);
        mUser.setFullName(str_fullName);
        mUser.setPassword(str_password);
        mUser.setAddress(str_address);
        mUser.setAge(iAge);
        mUser.setTypeUser(str_TypeUser);
        mUser.setSex(str_Sex);
        mUser.setContact(str_Contact);

        UserDatabase.getInstance(this).userDao().updateUser(mUser);
        Toast.makeText(this,"UpDate is Success!",Toast.LENGTH_LONG).show();
        Intent intentBack = new Intent();
        intentBack.putExtra("key3",mUser);
        setResult(RESULT_OK,intentBack);
        finish();




    }
}