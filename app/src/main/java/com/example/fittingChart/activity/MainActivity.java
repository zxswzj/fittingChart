package com.example.fittingChart.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fittingChart.database.DaoSession;
import com.example.fittingChart.database.GreenDaoHelper;
import com.example.fittingChart.fragment.RecordFragment;
import com.example.fittingChart.fragment.FittingFragment;
import com.example.fittingChart.fragment.UserFragment;
import com.example.fittingChart.database.User;
import com.github.mikephil.charting.charts.LineChart;
import com.example.fittingChart.R;

import java.util.List;


public class MainActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    private DaoSession session;
    String TAG = "SQLite";
    private TextView mTextMessage;
    private LineChart mLineChart;

    Toast toast;
    //ArrayList<Fragment> mFragments;
    //private Toolbar mToolbar;
    UserFragment userFragment;
    FittingFragment fittingFragment;
    RecordFragment recordFragment;
    int currentFrag;
    private int lastIndex;

    private void initCtrl(){
        currentFrag = 0;
        userFragment = new UserFragment();
        fittingFragment = new FittingFragment();
        recordFragment = new RecordFragment();
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
        Log.i(TAG, "MainActivity.onCreate");

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        initCtrl();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //尝试从SQLite中加载用户数据
        //SQLite
        //create/open Database
        session = GreenDaoHelper.getDaoSession(this);

        session.getUserDao().deleteAll();//清空所有记录
        List<User> users = session.getUserDao().loadAll();

        User u = users.get(0);

        Bundle bundle = new Bundle();
        bundle.putString("username",u.getUsername());
        bundle.putString("slogan",u.getSlogan());
        userFragment.setArguments(bundle);

//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        FittingStartFragment fragment = new FittingStartFragment();
//        ft.replace(R.id.fragment_container, fragment);
//        ft.commit();
        replaceFragmentPosition(0);
    }


    private void replaceFragmentPosition(int position) {
        Log.i("Fragment","MainActivity.setFragmentPosition");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(position)
        {
            case 0: ft.replace(R.id.fragment_container, userFragment);break;
            case 1: ft.replace(R.id.fragment_container, fittingFragment);break;
            case 2: ft.replace(R.id.fragment_container, recordFragment);break;
            default:
                Log.e("Fragment", "replaceFragmentPosition: ");
        }
        //ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void OnClicked(String name, String slogan) {
        Log.i("Fragment", "MainActivity.OnClicked");
        User u = new User("咸鱼","我爱学习，学习使我快乐", R.mipmap.ic_launcher);
        session.getUserDao().deleteAll();//清空所有记录
        session.getUserDao().insert(u);    }

}
