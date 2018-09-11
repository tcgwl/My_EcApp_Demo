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
import com.archer.lib.core.net.callback.IError;
import com.archer.lib.core.net.callback.IFailure;
import com.archer.lib.core.net.callback.ISuccess;
import com.archer.lib.core.util.log.LatteLogger;
import com.archer.lib.core.wechat.LatteWeChat;
import com.archer.lib.core.wechat.callbacks.IWeChatSignInCallback;
import com.archer.lib.ec.R;

/**
 * Created by Archer on 2018/2/9.
 */

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {
    TextInputEditText mEmailEt;
    TextInputEditText mPasswordEt;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    void onClickSignIn() {
        if (checkForm()) {
            Toast.makeText(Latte.getApplicationContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    //.url("http://192.168.1.145:8080/RestServer/api/user_profile.php")
                    .url("user_profile.php")
                    .params("email", mEmailEt.getText().toString())
                    .params("password", mPasswordEt.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
//                            String responseTmp = FileUtil.getRawFile(R.raw.user_profile);
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            LatteLogger.json("USER_PROFILE", "failure");
                        }
                    })
                    .error(new IError() {
                        @Override
                        public void onError(int code, String msg) {
                            LatteLogger.json("USER_PROFILE", "error");
                        }
                    })
                    .build()
                    .post();
        }
    }

    void onClickWeChat() {
        Toast.makeText(Latte.getApplicationContext(), "微信登录", Toast.LENGTH_SHORT).show();
        LatteWeChat
                .getInstance()
                .onSignSuccess(new IWeChatSignInCallback() {
                    @Override
                    public void onSignInSuccess(String userInfo) {
                        Toast.makeText(getContext(), userInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .signIn();
    }

    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmailEt.getText().toString();
        final String password = mPasswordEt.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEt.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmailEt.setError(null);
        }
        if (password.isEmpty() || password.length() < 6) {
            mPasswordEt.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPasswordEt.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mEmailEt = $(R.id.edit_sign_in_email);
        mPasswordEt = $(R.id.edit_sign_in_password);
        $(R.id.btn_sign_in).setOnClickListener(this);
        $(R.id.tv_link_sign_up).setOnClickListener(this);
        $(R.id.icon_sign_in_wechat).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) {
            onClickWeChat();
        }
    }
}
