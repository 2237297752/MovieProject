package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.LoginContract;
import com.bw.movie.model.bean.LoginBean;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.LoginView, LoginPresenter<LoginContract.LoginView>> implements LoginContract.LoginView{
    @BindView(R.id.login_image_fanhui)
    ImageView loginImageFanhui;
    @BindView(R.id.login_et_phone)
    EditText loginEtPhone;
    @BindView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @BindView(R.id.login_btn_wjmm)
    Button loginBtnWjmm;
    @BindView(R.id.login_text_register)
    TextView loginTextRegister;
    @BindView(R.id.login_btn_login)
    Button loginBtnLogin;
    @BindView(R.id.login_btn_weixin)
    Button loginBtnWeixin;
    private SharedPreferences sp;

    @Override
    protected void initData() {
        String encrypt = EncryptUtil.encrypt("123");
        Log.e("aaaaaa",encrypt);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        String newuseEmail = sp.getString("useEmail", "");
        String newusePwd = sp.getString("pwd", "");
        loginEtPhone.setText(newuseEmail);
        loginEtPwd.setText(newusePwd);
    }

    @Override
    protected LoginPresenter<LoginContract.LoginView> createPresenter() {
        return new LoginPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void getLoginView(String loginString) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(loginString, LoginBean.class);
        if ("0000".equals(loginBean.getStatus())){
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            int userId = loginBean.getResult().getUserId();
            String sessionId = loginBean.getResult().getSessionId();
            int sex = loginBean.getResult().getUserInfo().getSex();
            String email = loginBean.getResult().getUserInfo().getEmail();
            String headPic = loginBean.getResult().getUserInfo().getHeadPic();
            long lastLoginTime = loginBean.getResult().getUserInfo().getLastLoginTime();
            String nickName = loginBean.getResult().getUserInfo().getNickName();
            SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
            sp.edit().putString("email",email).commit();
            sp.edit().putString("headPic",headPic).commit();
            sp.edit().putString("nickName",nickName).commit();
            sp.edit().putString("lastLoginTime",String.valueOf(lastLoginTime)).commit();
            sp.edit().putInt("userId",userId).commit();
            sp.edit().putInt("sex",sex).commit();
            sp.edit().putString("sessionId",sessionId).commit();
            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            finish();
        }else if ("1001".equals(loginBean.getStatus())){
            Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_image_fanhui, R.id.login_btn_wjmm, R.id.login_text_register, R.id.login_btn_login, R.id.login_btn_weixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_image_fanhui:
                finish();
                break;
            case R.id.login_btn_wjmm:
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                break;
            case R.id.login_text_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.login_btn_login:
                String useEmail = loginEtPhone.getText().toString();
                String usePwd = EncryptUtil.encrypt(loginEtPwd.getText().toString());
                String pwd = loginEtPwd.getText().toString();
                if (TextUtils.isEmpty(useEmail)||TextUtils.isEmpty(usePwd)){
                    Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.commit();
                    mPresenter.requestLoginInfo(useEmail,usePwd);
                }
                break;
            case R.id.login_btn_weixin:
                break;
        }
    }
}
