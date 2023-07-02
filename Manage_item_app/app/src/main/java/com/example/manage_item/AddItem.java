package com.example.manage_item;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddItem extends AppCompatActivity {

    private EditText edtNameItem;
    private EditText edtIdItem;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        edtNameItem=findViewById(R.id.edtNameItem);
        edtIdItem = findViewById(R.id.edtIdItem);
        btnAddItem = findViewById(R.id.btnAdd_Item);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
    }

    private void addItem() {

        String strIdItem = edtIdItem.getText().toString().trim();
        String strNameItem = edtNameItem.getText().toString().trim();

        if(TextUtils.isEmpty(strIdItem)||TextUtils.isEmpty(strNameItem)){
            Toast.makeText(this,"There's still space left",Toast.LENGTH_LONG).show();
            return;
        }
        ProItem proItem = new ProItem(strIdItem,strNameItem);
        if (isItemExit(proItem)){
            Toast.makeText(this,"Account Exits",Toast.LENGTH_LONG).show();
            return;
        }

        ProItemDatabase.getInstance(this).proItemDao().insertItem(proItem);
        Toast.makeText(this,"AAD Item SUCCESSFULLY!",Toast.LENGTH_LONG).show();
        Intent intentBack = new Intent();
        intentBack.putExtra("key2",proItem);
        setResult(RESULT_OK,intentBack);
        finish();
    }

    private boolean isItemExit(ProItem proItem) {
        List<ProItem> list= ProItemDatabase.getInstance(this).proItemDao().checkItem(proItem.getNameItem(),proItem.getIdItem());
        return list != null && !list.isEmpty();
    }

}