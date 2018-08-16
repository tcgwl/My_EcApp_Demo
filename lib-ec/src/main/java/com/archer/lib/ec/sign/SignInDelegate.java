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
import com.archer.lib.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Archer on 2018/2/9.
 */

public class SignInDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmailEt;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPasswordEt;

    private ISignListener mISignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            Toast.makeText(Latte.getApplicationContext(), "验证通过", Toast.LENGTH_SHORT).show();
            RestClient.builder()
                    //.url("http://192.168.1.145:8080/RestServer/api/user_profile.php")
                    .url("user_profile.php")
                    .parmas("email", mEmailEt.getText().toString())
                    .parmas("password", mPasswordEt.getText().toString())
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

    @OnClick(R2.id.icon_sign_in_wechat)
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

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        start(new SignUpDelegate());
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

    }
}
