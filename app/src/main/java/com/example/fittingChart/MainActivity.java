package com.example.fittingChart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittingChart.autoLogin.DBHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.example.fittingChart.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Fragment_user.OnFragmentInteractionListener {

    private TextView mTextMessage;
    private LineChart mLineChart;
    private DBHelper db;
    Toast toast;
    //ArrayList<Fragment> mFragments;
    //private Toolbar mToolbar;
    Fragment_user fragment_user;
    Fragment_activity fragment_activity;
    Fragment_blank fragment_blank;
    int currentFrag;
    private int lastIndex;

    private void initCtrl(){
        currentFrag = 0;
        fragment_user = new Fragment_user();
        fragment_activity = new Fragment_activity();
        fragment_blank = new Fragment_blank();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragmentPosition(0);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragmentPosition(1);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragmentPosition(2);
                    return true;
            }
            return false;
        }
    };


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCtrl();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //尝试从SQLite中加载用户数据
        //SQLite
        //create/open Database
        Users u = new Users(1,"乐乐","我要吃山竹", R.mipmap.ic_launcher);
        db = new DBHelper(this);
        if(db.getUserCount() == 0)
            db.addUser(u);
        else
            u = db.getUser(1);

        Bundle bundle = new Bundle();
        bundle.putString("username",u.getUsername());
        bundle.putString("slogan",u.getSlogan());
        fragment_user.setArguments(bundle);
        replaceFragmentPosition(0);
    }


    private void replaceFragmentPosition(int position) {
        Log.i("Fragment","MainActivity.setFragmentPosition");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(position)
        {
            case 0: ft.replace(R.id.fragment_container, fragment_user);break;
            case 1: ft.replace(R.id.fragment_container, fragment_activity);break;
            case 2: ft.replace(R.id.fragment_container, fragment_blank);break;
            default:
                Log.e("Fragment", "replaceFragmentPosition: ");
        }
        ft.addToBackStack(null);
        ft.commit();
        //Fragment currentFragment = mFragments.get(position);
        //Fragment lastFragment = mFragments.get(lastIndex);
        //lastIndex = position;
//        ft.hide(lastFragment);
//        if (!currentFragment.isAdded()) {
//            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
//            ft.add(R.id.fragment_container, currentFragment);
//        }
//        ft.show(currentFragment);
//        ft.commitAllowingStateLoss();
    }

    @Override
    public void OnClicked(String name, String slogan) {
        Log.i("Fragment", "MainActivity.OnClicked");
        db.updateUser(new Users(1,name,slogan,R.mipmap.ic_launcher));
    }
}
