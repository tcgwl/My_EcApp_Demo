package com.archer.lib.core.wechat.templates;

import com.archer.lib.core.wechat.BaseWXEntryActivity;
import com.archer.lib.core.wechat.LatteWeChat;

/**
 * Created by Archer on 2018/2/26.
 *
 * 微信登录完成之后会返回到在该模板基础上生成的Activity，为此采取迂回办法：
 * 1.在onResume中finish
 * 2.设置透明主题
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);//不需要动画
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
