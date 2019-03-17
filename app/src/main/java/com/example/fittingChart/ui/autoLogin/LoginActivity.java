package com.example.fittingChart.ui.autoLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fittingChart.activity.MainActivity;
import com.example.fittingChart.R;

/**
 * 登录页面，git测试
 * create by seanz
 */
public class LoginActivity extends Activity {
    /**
     * 用户名
     */
    private EditText edt_username;

    /**
     * 密码
     */
    private EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        edt_username = (EditText) findViewById(R.id.edit_userName);
        edt_password = (EditText) findViewById(R.id.edit_password);
        findViewById(R.id.btn_login).setOnClickListener(mOnClickListener);
    }


    private View.OnClickListener mOnClickListener = new View.OnClickListener() {


        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_login://登录
                    String userName = edt_username.getText().toString();
                    String userPwd = edt_password.getText().toString();
                    UserManage.getInstance().saveUserInfo(LoginActivity.this, userName, userPwd);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);//跳转到主页
                    startActivity(intent);
                    finish();
                    break;
            }

        }
    };
}
