package com.example.fittingChart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.example.fittingChart.R;
import com.example.fittingChart.fragment.FittingStartFragment;
import com.example.fittingChart.fragment.UserFragment;

public class FittingActivity extends AppCompatActivity {

    FittingStartFragment fittingStartFragment;
    String table;

    public FittingActivity() {
        Log.i("Fragment","FittingActivity.FittingActivity");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitting);
        Log.i("Fragment","FittingActivity.onCreate");
        Intent intent = getIntent();
        table = intent.getStringExtra("table");

        fittingStartFragment = new FittingStartFragment();
        //userFragment = new UserFragment();
        replaceFragmentPosition(0);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fitting_fragment_container, fittingStartFragment);
//        ft.commit();
    }

    private void replaceFragmentPosition(int position) {
        Log.i("Fragment","FittingActivity.replaceFragmentPosition");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(position)
        {
            case 0: ft.replace(R.id.fitting_fragment_container, fittingStartFragment);break;
            default:
        }
        //ft.addToBackStack(null);
        ft.commit();
    }

}
