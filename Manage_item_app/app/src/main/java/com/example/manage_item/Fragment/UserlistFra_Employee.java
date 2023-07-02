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
import com.example.manage_item.Profile;
import com.example.manage_item.R;
import com.example.manage_item.UpdateUser;
import com.example.manage_item.User;
import com.example.manage_item.UserAdapter;
import com.example.manage_item.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserlistFra_Employee extends Fragment {

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
        View view = inflater.inflate(R.layout.frag_user_list_employee,container,false);
         edtSeach = view.findViewById(R.id.edtSearchUser_Em);
         rcvListUser = view.findViewById(R.id.rcvListUser_Em);
         userAdapter = new UserAdapter(new UserAdapter.iclickItem() {
            @Override
            public void updateUser(User user) {
            }

            @Override
            public void deleteUser(User user) {
            }

             @Override
             public void clickUser(User user) {
                 iclick_User(user);
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

        return view;
    }

    private void iclick_User(User user) {
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


}
