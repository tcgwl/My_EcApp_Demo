package com.archer.lib.core.wechat.templates;

import com.archer.lib.core.wechat.BaseWXEntryActivity;
import com.archer.lib.core.wechat.LatteWeChat;

/**
 * Created by Archer on 2018/2/26.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
