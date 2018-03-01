package com.archer.lib.core.app;

import com.archer.lib.core.util.storage.LattePreference;

/**
 * Created by Archer on 2018/2/24.
 */

public class AccountManager {
    private enum SignTag {
        SIGN_TAG
    }

    /**
     * 保存用户登录状态，登录后调用
     */
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(), state);
    }

    private static boolean isSignIn() {
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
