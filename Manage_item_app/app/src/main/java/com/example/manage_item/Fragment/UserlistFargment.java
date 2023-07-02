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

import com.example.manage_item.AddUser;
import com.example.manage_item.MyProfile;
import com.example.manage_item.Profile;
import com.example.manage_item.R;
import com.example.manage_item.UpdateUser;
import com.example.manage_item.User;
import com.example.manage_item.UserAdapter;
import com.example.manage_item.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserlistFargment extends Fragment {

    private EditText edtSeach;
    private Button btnAddUser;
    private TextView tvDeleteAll;
    private RecyclerView rcvListUser;

    private List<User> mListUser;

    UserAdapter userAdapter;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== RESULT_OK){
                        Intent intent = result.getData();
                        loadData();
                    }
                }
            }
    );



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list,container,false);
         edtSeach = view.findViewById(R.id.edtSearchUser);
         btnAddUser = view.findViewById(R.id.btnAddUser);
         tvDeleteAll = view.findViewById(R.id.tvDeleteAll);
         rcvListUser = view.findViewById(R.id.rcvListUser);


        userAdapter = new UserAdapter(new UserAdapter.iclickItem() {
            @Override
            public void updateUser(User user) {
                iclickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                iclickDeleteUser(user);
            }

            @Override
            public void clickUser(User user) {
                ilick_User(user);
            }
        });

         mListUser = new ArrayList<>();
         userAdapter.setData(mListUser);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvListUser.setLayoutManager(layoutManager);
        rcvListUser.setAdapter(userAdapter);

        edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handelSearchUser();
                }

                return false;
            }
        });
        loadData();

        tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeleteAllAccount();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }

        });
        return view;
    }

    private void ilick_User(User user) {
        Intent intent = new Intent(getActivity(), Profile.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("transmit_User",user);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void handelSearchUser() {
       String strKeyWord = edtSeach.getText().toString().trim();
        mListUser = new ArrayList<>();
        mListUser = UserDatabase.getInstance(getContext()).userDao().SearchUser(strKeyWord);
        userAdapter.setData(mListUser);
    }

    private  void loadData () {
        mListUser = UserDatabase.getInstance(getContext()).userDao().getListUser();
        userAdapter.setData(mListUser);
    }

    private void addUser() {
        Intent intent = new Intent(getActivity(),AddUser.class);
        mActivityResultLauncher.launch(intent);
    }

    public void iclickUpdateUser(User user){
        Intent intent = new Intent(getActivity(), UpdateUser.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key_id",user);
        intent.putExtras(bundle);
        mActivityResultLauncher.launch(intent);
    }

    public void iclickDeleteUser(User user){
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Delete Account")
                .setMessage("Are You Sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete Account
                        UserDatabase.getInstance(getContext()).userDao().deleteUser(user);
                        Toast.makeText(getContext(),"Delete User Success!",Toast.LENGTH_LONG).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }

    private void clickDeleteAllAccount(){
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Delete All Account")
                .setMessage("Are You Sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //delete Account
                        UserDatabase.getInstance(getContext()).userDao().deleteAllUser();
                        Toast.makeText(getContext(),"Delete All User Success!",Toast.LENGTH_LONG).show();
                        loadData();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}
