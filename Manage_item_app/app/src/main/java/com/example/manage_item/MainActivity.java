package com.example.manage_item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtUserNameId;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView tvFgPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserNameId = findViewById(R.id.edtUser_Name_Id);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvFgPassword = findViewById(R.id.tvForgot_password);

        List<User> mlist;
        mlist = UserDatabase.getInstance(this).userDao().getListUser();

        if(!isDatabaseExit(mlist)){
            UserDatabase.getInstance(this).userDao().insertUser(new User("ET20203804", "05062002","Pham Phuong Nam"
                    ,20,"Nam","Manager","Ha Noi","0367289425"));
            UserDatabase.getInstance(this).userDao().insertUser(new User("ET20218346", "28082002","Pham Thi Duy Linh"
                    ,20,"Nu","Employee","Hai Duong","0333234567"));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              LogIn();
            }
        });



    }
    private void LogIn() {
        String strIdUser = edtUserNameId.getText().toString().trim();
        String strPasswordUser = edtPassword.getText().toString().trim();
        if(TextUtils.isEmpty(strIdUser)||TextUtils.isEmpty(strPasswordUser)){
            Toast.makeText(this,"Id Or Password Is Blank",Toast.LENGTH_LONG).show();
            return;
        }
        User user = new User(strIdUser,strPasswordUser);
        if (isAccountExit(user)){
            String Type = checkTypeUser(strIdUser);
           if (Type.equals("Manager")){
               Intent intent1 = new Intent(MainActivity.this,Mangage.class);
               Bundle bundle =new Bundle();
               bundle.putString("Id_key",strIdUser);
               intent1.putExtras(bundle);
               startActivity(intent1);
           }
           else if (Type.equals("Employee")){
               Intent intent2 = new Intent(MainActivity.this,Employee.class);
               Bundle bundle =new Bundle();
               bundle.putString("Id_key",strIdUser);
               intent2.putExtras(bundle);
               startActivity(intent2);
           } else {
               Toast.makeText(this,"Type does not exist",Toast.LENGTH_LONG).show();
           }
        }else{
            Toast.makeText(this,"Account does not exist",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isDatabaseExit(List<User> list){
        return list != null && !list.isEmpty();
    }


    private boolean isAccountExit (User user){
        List<User> list= UserDatabase.getInstance(this).userDao().checkAccount(user.getUserNameId(),user.getPassword());
        return list != null && !list.isEmpty();
    }
    private String checkTypeUser(String idUser){
        String Type = UserDatabase.getInstance(this).userDao().getTypeById(idUser);
        return Type;
    }


}