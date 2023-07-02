package com.example.manage_item;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private ImageView imageView;
    private Button btnChooseIm;
    private Button btnDetect;
    private TextView tvResult;

    private History history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        String strGetId = bundle.getString("transmit_key_Home");
        User user = UserDatabase.getInstance(this).userDao().getUserById(strGetId);

        imageView = findViewById(R.id.imvDetect);
        btnChooseIm = findViewById(R.id.btnChooseImage);
        btnDetect = findViewById(R.id.btnDetect);
        tvResult = findViewById(R.id.tvResult);

        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Xử lý ảnh được chọn tại đây
                        imageView.setImageURI(uri);
                    }
                });

// Khi người dùng nhấn nút chọn ảnh
        btnChooseIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });

        btnDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveHistory();

            }
        });
    }

    private void saveHistory() {
        Calendar calendar = Calendar.getInstance();
        String dateTime = calendar.getTime().toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = simpleDateFormat.format(calendar.getTime());
        String strTime_save = formattedDateTime ;
        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        String strGetId = bundle.getString("transmit_key_Home");
        User user = UserDatabase.getInstance(this).userDao().getUserById(strGetId);
        String strResult = tvResult.getText().toString().trim();
        String strNameUser = user.getFullName().toString().trim();
        String strIdUser =user.getUserNameId().toString().trim();

        history = new History(strIdUser,strNameUser,strResult,strTime_save);

        HistoryDatabase.getInstance(this).historyDao().insertHistory(history);
        Toast.makeText(this,"Save Finish",Toast.LENGTH_LONG).show();


    }
}