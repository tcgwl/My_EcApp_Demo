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

/**
 * Created by Archer on 2018/2/9.
 */

public class SignUpDelegate extends LatteDelegate {
    TextInputEditText mNameEt;
    TextInputEditText mEmailEt;
    TextInputEditText mPhoneEt;
    TextInputEditText mPasswordEt;
    TextInputEditText mRePasswordEt;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    void onClickSignUp() {
        if (checkForm()) {
            Toast.makeText(Latte.getApplicationContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    //.url("http://192.168.1.145:8080/RestServer/api/user_profile.php")
                    .url("user_profile.php")
                    .params("name", mNameEt.getText().toString())
                    .params("email", mNameEt.getText().toString())
                    .params("phone", mNameEt.getText().toString())
                    .params("password", mNameEt.getText().toString())
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

    void onClickLink() {
        getSupportDelegate().start(new SignInDelegate());
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
        mNameEt = $(R.id.edit_sign_up_name);
        mEmailEt = $(R.id.edit_sign_up_email);
        mPhoneEt = $(R.id.edit_sign_up_phone);
        mPasswordEt = $(R.id.edit_sign_up_password);
        mRePasswordEt = $(R.id.edit_sign_up_re_password);

        $(R.id.btn_sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });

        $(R.id.tv_link_sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLink();
            }
        });
    }
}
