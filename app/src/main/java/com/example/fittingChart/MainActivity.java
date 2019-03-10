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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private LineChart mLineChart;
    private DBHelper db;
    Toast toast;
    List<Fragment> mFragments;
    //private Toolbar mToolbar;
    private int lastIndex;

    private void initCtrl(){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initCtrl();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId())
                        {
                            case R.id.navigation_home:
                                toast=Toast.makeText(getApplicationContext(), "page1", Toast.LENGTH_SHORT);
                                toast.show();
                                setFragmentPosition(0);
                                break;
                            case R.id.navigation_notifications:
                                toast=Toast.makeText(getApplicationContext(), "page2", Toast.LENGTH_SHORT);
                                toast.show();
                                setFragmentPosition(1);
                                break;
                            case R.id.navigation_dashboard:
                                toast=Toast.makeText(getApplicationContext(), "page3", Toast.LENGTH_SHORT);
                                toast.show();
                                setFragmentPosition(2);
                                break;
                        }
                        return false;
                    }
                }
        );


        //init Fragment
        //setSupportActionBar(mToolbar);
        mFragments = new List<>();
        mFragments.add(new Fragment_user());
        mFragments.add(new Fragment_activity());
        mFragments.add(new Fragment_blank());
        setFragmentPosition(0);


        //尝试从SQLite中加载用户数据
        //SQLite
        //create/open Database
        Users u = new Users(1,"le","sdfds");
//        u.setID(1);
//        u.setUsername("乐乐啊");
//        u.setSlogan("我要回家");
        db = new DBHelper(this);
        if(db.getUserCount() == 0)
        {
            db.addUser(u);
        }
        else
        {
            u = db.getUser(1);
            String str1 = u.getUsername();
//            et_username.setText("aaa");
//            et_slogan.setText("def");
        }
        Bundle bundle = new Bundle();
        bundle.putString("username",u.getUsername());
        bundle.putString("slogan",u.getSlogan());
        Fragment_user fu = mFragments.get(0);
        //Users u = db.getUser(0);
        //et_username.setText(u.getUsername());
        //et_slogan.setText(u.getSlogan());


        //create table
        //db.execSQL("CREATE TABLE IF NOT EXISTS user(username VARCHAR,slogan VARCHAR);");
 /*
        mLineChart = findViewById(R.id.chart1);
        //显示边界
        mLineChart.setDrawBorders(true);
        //设置数据
        List<Entry> entry1 = new ArrayList<>();
        List<Entry> entry2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entry1.add(new Entry(i, new Random().nextInt(300)));
            entry2.add(new Entry(i, new Random().nextInt(200)));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet1 = new LineDataSet(entry1, "Label");
        LineDataSet lineDataSet2 = new LineDataSet(entry2, "aaa");

        //LineData lineData  = new LineData(dataSet);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        LineData lineData = new LineData(dataSets);

        mLineChart.setData(lineData);
        mLineChart.invalidate();
*/




//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private void setFragmentPosition(int position) {
        Log.i("Fragment","MainActivity.setFragmentPosition");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = mFragments.get(position);
        Fragment lastFragment = mFragments.get(lastIndex);
        lastIndex = position;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.ll_frameLayout, currentFragment);

        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();

    }

//    public void btn_update_user_info_cb(View view){
//        Log.i("SQLite","btn_update_user_info_cb");
//
//        Users u = new Users();
//        u.setUsername("乐乐");
//        u.setSlogan("我要吃山竹");
//        if(db.getUser(1) == null)
//            db.addUser(u);
//        else
//        {
//            db.updateUser(u);
//        }
//
////        List<Users> users = db.getAllUsers();
////        for (Users cn : users) {
////            String log = "Id: "+cn.getID()+" ,Name: " + cn.getUsername() + " ,Slogan: " + cn.getSlogan();
////            Log.i("SQLite: ", log);
////        }
//        Bundle bundle = new Bundle();
//        bundle.putString("name","大笨蛋");
//        bundle.putString("slogan","我要出去玩");
//        mFragments.get(0).setArguments(bundle);
//    }
}
