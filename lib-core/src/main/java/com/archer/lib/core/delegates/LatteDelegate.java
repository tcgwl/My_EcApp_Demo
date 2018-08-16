package com.archer.lib.core.delegates;

/**
 * Created by Archer on 2018/2/6.
 */

public abstract class LatteDelegate extends PermissionCheckerDelegate {

    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}
