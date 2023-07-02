package com.example.manage_item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    private EditText edtCurrent_Password;
    private EditText edtNew_Password;
    private EditText edtRe_Enter_Password;
    private Button btnChange_Password;
    private User user;
    String strCurrentPassword;
    String strNewPassword;
    String strRe_NewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtCurrent_Password = findViewById(R.id.edtEnterCurrentPassword);
        edtNew_Password = findViewById(R.id.edtEnterNewPassword);
        edtRe_Enter_Password = findViewById(R.id.edtRe_EnterPassword);
        btnChange_Password = findViewById(R.id.btnChangePassword);


        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        String strGetId = bundle.getString("transmit_key1");
        user = UserDatabase.getInstance(this).userDao().getUserById(strGetId);

        btnChange_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(user);

            }
        });


    }

    private void changePassword(User user) {
        strCurrentPassword = edtCurrent_Password.getText().toString().trim();
        strNewPassword = edtNew_Password.getText().toString().trim();
        strRe_NewPassword = edtRe_Enter_Password.getText().toString().trim();

        if(TextUtils.isEmpty(strCurrentPassword)||TextUtils.isEmpty(strNewPassword)||TextUtils.isEmpty(strRe_NewPassword))
               {
            Toast.makeText(this,"There's still space left",Toast.LENGTH_LONG).show();
            return;
        }

        if(!strCurrentPassword.equals(user.getPassword())){
            Toast.makeText(this,"Current Password Incorrect",Toast.LENGTH_LONG).show();
            return;
        }

        user.setPassword(strNewPassword);
        UserDatabase.getInstance(this).userDao().updateUser(user);
        Toast.makeText(this,"Change Password Successfully",Toast.LENGTH_LONG).show();
        finish();
    }

}