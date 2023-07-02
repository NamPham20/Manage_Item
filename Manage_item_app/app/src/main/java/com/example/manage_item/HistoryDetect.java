package com.example.manage_item;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryDetect extends AppCompatActivity {
    private EditText edtSeach;
    private RecyclerView rcvListHistory;
    private TextView tvDeleteAllHistory;

    private List<History> mlistHistory;
    private HistoryAdapter historyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detect);

        edtSeach = findViewById(R.id.edtSearch_history);
        rcvListHistory = findViewById(R.id.rcvList_History);
        tvDeleteAllHistory = findViewById(R.id.tvDeleteAll_History);

        historyAdapter =new HistoryAdapter();
        mlistHistory = new ArrayList<>();
        historyAdapter.setData(mlistHistory);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvListHistory.setLayoutManager(layoutManager);
        rcvListHistory.setAdapter(historyAdapter);

        edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handelSearchUser();
                }

                return false;
            }
        });

        tvDeleteAllHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHistory();
            }
        });


        loadData ();
    }

    private void deleteHistory() {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm Delete All Account")
                    .setMessage("Are You Sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //delete Account
                            HistoryDatabase.getInstance(HistoryDetect.this).historyDao().deleteAllHistory();
                            Toast.makeText(HistoryDetect.this,"Delete All User Success!",Toast.LENGTH_LONG).show();
                            loadData();
                        }
                    })
                    .setNegativeButton("No",null)
                    .show();
    }

    private void handelSearchUser() {
        String strKeyWord = edtSeach.getText().toString().trim();
        mlistHistory = new ArrayList<>();
        mlistHistory = HistoryDatabase.getInstance(this).historyDao().SearchHistory(strKeyWord);
        historyAdapter.setData(mlistHistory);
    }

    private  void loadData () {
        mlistHistory = HistoryDatabase.getInstance(this).historyDao().getListHistory();
        historyAdapter.setData(mlistHistory);
    }
}