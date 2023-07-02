package com.example.manage_item.Fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manage_item.ProItem;
import com.example.manage_item.ProItemAdapter;
import com.example.manage_item.ProItemDatabase;
import com.example.manage_item.R;

import java.util.ArrayList;
import java.util.List;

public class ProhibitedItemsFra_Employee extends Fragment {

    private EditText edtSeach;
    private RecyclerView rcvListItem;


    private List<ProItem> mlistItem;
    private ProItemAdapter proItemAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prohibited_items_em, container, false);

        edtSeach = view.findViewById(R.id.edtSearchItem_em);
        rcvListItem = view.findViewById(R.id.rcvListItem_em);

        proItemAdapter = new ProItemAdapter(new ProItemAdapter.iclick() {
            @Override
            public void iclikDelete(ProItem proItem) {
            }
        });

        mlistItem = new ArrayList<>();
        proItemAdapter.setData(mlistItem);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvListItem.setLayoutManager(layoutManager);
        rcvListItem.setAdapter(proItemAdapter);


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

    private  void loadData () {
        mlistItem = ProItemDatabase.getInstance(getContext()).proItemDao().getListItem();
        proItemAdapter.setData(mlistItem);
    }


}
