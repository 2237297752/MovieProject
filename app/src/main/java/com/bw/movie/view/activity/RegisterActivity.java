package com.bw.movie.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.model.bean.EmailBean;
import com.bw.movie.model.bean.RegisterBean;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterContract.RegistView, RegisterPresenter<RegisterContract.RegistView>>implements RegisterContract.RegistView {

    @BindView(R.id.login_image_fanhui)
    ImageView loginImageFanhui;
    @BindView(R.id.register_et_name)
    EditText registerEtName;
    @BindView(R.id.register_et_phone)
    EditText registerEtPhone;
    @BindView(R.id.register_et_pwd)
    EditText registerEtPwd;
    @BindView(R.id.register_image_delete)
    ImageView registerImageDelete;
    @BindView(R.id.register_image_eye)
    ImageView registerImageEye;
    @BindView(R.id.register_et_yanzhengma)
    EditText registerEtYanzhengma;
    @BindView(R.id.register_btn_getyanzhengma)
    Button registerBtnGetyanzhengma;
    @BindView(R.id.register_tv_login)
    TextView registerTvLogin;
    @BindView(R.id.register_btn_register)
    Button registerBtnRegister;

    private int anInt = 120;
    public android.os.Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 11){
                registerBtnGetyanzhengma.setText("重新获取("+anInt+""+"s)");
                anInt--;
                if (anInt>=0){
                    handler.sendEmptyMessageDelayed(11,1000);
                }else {
                    anInt = 120;
                    registerBtnGetyanzhengma.setText("获取验证码");
                }
            }
        }
    };
    @Override
    protected void initData() {

    }

    @Override
    protected RegisterPresenter<RegisterContract.RegistView> createPresenter() {
        return new RegisterPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }
    @Override
    public void getRegisView(String registString) {
        Gson gson = new Gson();
        RegisterBean registerBean = gson.fromJson(registString, RegisterBean.class);
        if ("0000".equals(registerBean.getStatus())){
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            finish();
        } else if ("1001".equals(registerBean.getStatus())) {
            Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCaptchaView(String captchString) {
        Gson gson = new Gson();
        EmailBean emailBean = gson.fromJson(captchString, EmailBean.class);
        Toast.makeText(this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(emailBean.getStatus())) {
            Toast.makeText(this, emailBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
        handler.sendEmptyMessageDelayed(11,1000);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_image_fanhui, R.id.register_image_delete, R.id.register_image_eye, R.id.register_btn_getyanzhengma, R.id.register_tv_login, R.id.register_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_image_fanhui:
                finish();
                break;
            case R.id.register_image_delete:

                break;
            case R.id.register_image_eye:

                break;
            case R.id.register_btn_getyanzhengma:
                if (anInt == 120){
                    String captch = registerEtPhone.getText().toString();
                    mPresenter.requestCaptchInfo(captch);
                }
                break;
            case R.id.register_tv_login:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
            case R.id.register_btn_register:
                String nickNameRegist = registerEtName.getText().toString();
                String pwdRegist = EncryptUtil.encrypt(registerEtPwd.getText().toString());
                String captch = registerEtPhone.getText().toString();
                String codeRegist = registerEtYanzhengma.getText().toString();
                mPresenter.requestRegistInfo(nickNameRegist,pwdRegist,captch,codeRegist);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(11);
    }
}
