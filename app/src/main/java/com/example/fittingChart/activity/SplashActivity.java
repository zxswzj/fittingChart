package com.example.fittingChart.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fittingChart.R;

import java.util.ArrayList;
import java.util.Random;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * 启动页，app刚打开时的activity
 * create by seanz
 */
public class SplashActivity extends Activity {

    //public static Typeface typeface ;


    private final int SPLASH_DISPLAY_LENGTH = 2000; // 两秒后进入系统
    //ArrayList<String> sentence;
    String[] sentence;
    ArrayList<String> sentenceList;
    TextView tv_sentence;
    TextView tv_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,
                        MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }

        }, SPLASH_DISPLAY_LENGTH);

        tv_sentence = findViewById(R.id.splash_tv_sentense);
        tv_author = findViewById(R.id.splash_tv_author);
//        try{
//            typeface = Typeface.createFromAsset(getAssets(), "fonts/URANIA.ttf");
//        }catch (Exception e)
//        {
//            Log.i("Fragment", "onCreate: load typeface fail");
//            typeface = null;
//        }

        //tv_sentence.setTypeface(typeface);

        sentence = getResources().getStringArray(R.array.splashSentence);
        sentenceList = new ArrayList<>();
        for (String str : sentence) {
            sentenceList.add(str);
        }

        int n = sentenceList.size();
        int idx = new Random().nextInt(n);
        String str = sentenceList.get(idx);
        String strAuthor = str.substring(str.indexOf("[AUTHOR]"), str.length()-1);
        String strSentence = str.substring(0,str.indexOf("[AUTHOR]"));
        tv_sentence.setText(strSentence);
        tv_author.setText(strAuthor);
    }

    void loadSentence()
    {
        sentence = getResources().getStringArray(R.array.splashSentence);
    }

//        if (UserManage.getInstance().hasUserInfo(this))//自动登录判断，SharePrefences中有数据，则跳转到主页，没数据则跳转到登录页
//        {
//            Log.i("LOGIN","found user data, branch to home");
//            mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
//        } else {
//            Log.i("LOGIN","no user data found, branch to login");
//            mHandler.sendEmptyMessageAtTime(GO_LOGIN, 2000);
//        }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
}

}