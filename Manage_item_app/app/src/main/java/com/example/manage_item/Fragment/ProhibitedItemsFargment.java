package com.example.manage_item.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manage_item.AddItem;
import com.example.manage_item.ProItem;
import com.example.manage_item.ProItemAdapter;
import com.example.manage_item.ProItemDatabase;
import com.example.manage_item.R;

import java.util.ArrayList;
import java.util.List;

public class ProhibitedItemsFargment extends Fragment {

    private EditText edtSeach;
    private RecyclerView rcvListItem;

    private Button btnAddItem;

    private List<ProItem> mlistItem;
    private ProItemAdapter proItemAdapter;







    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        loadData ();

                    }
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prohibited_items, container, false);

        edtSeach = view.findViewById(R.id.edtSearchItem);
        rcvListItem = view.findViewById(R.id.rcvListItem);
        btnAddItem = view.findViewById(R.id.btnAddItem);

        proItemAdapter = new ProItemAdapter(new ProItemAdapter.iclick() {
            @Override
            public void iclikDelete(ProItem proItem) {
                deleteItem(proItem);
            }
        });

        mlistItem = new ArrayList<>();
        proItemAdapter.setData(mlistItem);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvListItem.setLayoutManager(layoutManager);
        rcvListItem.setAdapter(proItemAdapter);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddItem.class);
                mActivityResultLauncher.launch(intent);
            }
        });

        edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handelSearchUser();
                }

                return false;
            }
        });

        loadData ();
        return view;
    }

    private void handelSearchUser() {
        String strKeyWord = edtSeach.getText().toString().trim();
        mlistItem = new ArrayList<>();
        mlistItem = ProItemDatabase.getInstance(getContext()).proItemDao().SearchItem(strKeyWord);
        proItemAdapter.setData(mlistItem);
    }

    private void deleteItem(ProItem proItem) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Delete Account")
                .setMessage("Are You Sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete Account
                        ProItemDatabase.getInstance(getContext()).proItemDao().deleteUser(proItem);
                        Toast.makeText(getContext(),"Delete Item Success!",Toast.LENGTH_LONG).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private  void loadData () {
        mlistItem = ProItemDatabase.getInstance(getContext()).proItemDao().getListItem();
        proItemAdapter.setData(mlistItem);
    }


}
