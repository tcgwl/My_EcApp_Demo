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
import com.archer.lib.ec.launcher.LauncherDelegate;
import com.archer.lib.ec.main.EcBottomDelegate;
import com.archer.lib.ec.sign.ISignListener;
import com.archer.lib.ec.sign.SignInDelegate;

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
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
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
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
