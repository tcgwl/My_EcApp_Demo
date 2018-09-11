package com.archer.android.ecapp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.archer.lib.core.activities.ProxyActivity;
import com.archer.lib.core.app.Latte;
import com.archer.lib.core.delegates.LatteDelegate;
import com.archer.lib.core.ui.launcher.ILauncherListener;
import com.archer.lib.core.ui.launcher.OnLauncherFinishTag;
import com.archer.lib.ec.main.EcBottomDelegate;
import com.archer.lib.ec.sign.ISignListener;
import com.archer.lib.ec.sign.SignInDelegate;

import qiu.niorgai.StatusBarCompat;

public class DemoActivity extends ProxyActivity implements 
        ISignListener,
        ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new EcBottomDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_SHORT).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }

    @Override
    public void post(Runnable runnable) {

    }
}
