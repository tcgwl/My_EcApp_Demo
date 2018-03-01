package com.archer.lib.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.archer.lib.core.app.Latte;
import com.archer.lib.core.delegates.LatteDelegate;
import com.archer.lib.core.net.RestClient;
import com.archer.lib.core.net.callback.ISuccess;
import com.archer.lib.core.util.log.LatteLogger;
import com.archer.lib.ec.R;
import com.archer.lib.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Archer on 2018/2/9.
 */

public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mNameEt;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmailEt;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhoneEt;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPasswordEt;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePasswordEt;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            Toast.makeText(Latte.getApplicationContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
//                    .url("http://127.0.0.1/index")
                    .url("http://192.168.1.145:8080/RestServer/api/user_profile.php")
                    .parmas("name", mNameEt.getText().toString())
                    .parmas("email", mNameEt.getText().toString())
                    .parmas("phone", mNameEt.getText().toString())
                    .parmas("password", mNameEt.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
//                            String responseTmp = FileUtil.getRawFile(R.raw.user_profile);
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink() {
        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mNameEt.getText().toString();
        final String email = mEmailEt.getText().toString();
        final String phone = mPhoneEt.getText().toString();
        final String password = mPasswordEt.getText().toString();
        final String rePassword = mRePasswordEt.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mNameEt.setError("请输入姓名");
            isPass = false;
        } else {
            mNameEt.setError(null);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEt.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmailEt.setError(null);
        }
        if (phone.isEmpty() || phone.length() != 11) {
            mPhoneEt.setError("手机号码错误");
            isPass = false;
        } else {
            mPhoneEt.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPasswordEt.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPasswordEt.setError(null);
        }
        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePasswordEt.setError("密码验证错误");
            isPass = false;
        } else {
            mRePasswordEt.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
