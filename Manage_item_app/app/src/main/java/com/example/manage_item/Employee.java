package com.example.manage_item;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.manage_item.Fragment.ProhibitedItemsFra_Employee;
import com.example.manage_item.Fragment.UserlistFra_Employee;
import com.google.android.material.navigation.NavigationView;

public class Employee extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_USER_LIST = 1;
    private static final int FRAGMENT_PROHIBITED_ITEMS = 2;
    private static final int FRAGMENT_MY_PROFILE = 3;
    private static final int FRAGMENT_HISTORY = 4;
    private static final int FRAGMENT_CHANGE_PASSWORD = 5;

    private int currentFragment =-1;

    private  String StrIdTransmit;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.frame_view_profile, null);
        frameLayout = view.findViewById(R.id.frameViewFragment_profile);

        Toolbar toolbar = findViewById(R.id.EtoolBar);
        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.EdrawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = getIntent();
        Bundle bundle= i.getExtras();
        String strGetId = bundle.getString("Id_key");
        User user = UserDatabase.getInstance(this).userDao().getUserById(strGetId);

        NavigationView navigationView= findViewById(R.id.navigation_view_Employee);
        View headerView = navigationView.getHeaderView(0);
        TextView headerTvName = headerView.findViewById(R.id.head_nav_Name);
        headerTvName.setText(user.getFullName());

        TextView headerTvNameId = headerView.findViewById(R.id.head_nav_id);
        headerTvNameId.setText(user.getUserNameId());

        StrIdTransmit = headerTvNameId.getText().toString();

        TextView headerTvPassword  = headerView.findViewById(R.id.head_nav_Password);
        headerTvPassword.setText(user.getPassword());

        navigationView.setNavigationItemSelectedListener(this);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nv_home){
            if(currentFragment != FRAGMENT_HOME){
                Intent intent = new Intent(this,Home.class);
                Bundle bundle_home =new Bundle();
                bundle_home.putString("transmit_key_Home", StrIdTransmit);
                intent.putExtras(bundle_home);
                startActivity(intent);
                currentFragment = FRAGMENT_HOME;
            }

        } else if (id == R.id.nv_user_list) {
            if(currentFragment != FRAGMENT_USER_LIST){
                replaceFragment(new UserlistFra_Employee());
                currentFragment = FRAGMENT_USER_LIST;
            }

        }else if (id == R.id.nv_prohibited_items) {
            if(currentFragment != FRAGMENT_PROHIBITED_ITEMS){
                replaceFragment(new ProhibitedItemsFra_Employee());
                currentFragment = FRAGMENT_PROHIBITED_ITEMS;
            }

        }else if (id == R.id.nv_my_profile) {
            if(currentFragment != FRAGMENT_MY_PROFILE){
                Intent intent = new Intent(Employee.this, MyProfile.class);
                Bundle bundle =new Bundle();
                bundle.putString("transmit_key", StrIdTransmit);
                intent.putExtras(bundle);
                startActivity(intent);
                currentFragment = FRAGMENT_MY_PROFILE;
            }

        }else if (id == R.id.nv_history) {
            if(currentFragment != FRAGMENT_HISTORY){
                Intent intent1 = new Intent(Employee.this, HistoryDetect_Em.class);
                Bundle bundle =new Bundle();
                bundle.putString("transmit_key2", StrIdTransmit);
                intent1.putExtras(bundle);
                startActivity(intent1);
                currentFragment = FRAGMENT_HISTORY;
            }

        }else if (id == R.id.nv_change_password) {
            if(currentFragment != FRAGMENT_CHANGE_PASSWORD){
                Intent intent1 = new Intent(Employee.this, ChangePassword.class);
                Bundle bundle =new Bundle();
                bundle.putString("transmit_key1", StrIdTransmit);
                intent1.putExtras(bundle);
                startActivity(intent1);
                currentFragment = FRAGMENT_CHANGE_PASSWORD;
            }

        }else if (id == R.id.nv_logout) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Econten_frame,fragment);
        transaction.commit();
    }
}