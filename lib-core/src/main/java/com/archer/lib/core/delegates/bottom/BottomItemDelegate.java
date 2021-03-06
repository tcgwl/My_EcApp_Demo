package com.archer.lib.core.delegates.bottom;

import android.widget.Toast;

import com.archer.lib.core.delegates.LatteDelegate;

/**
 * Created by Archer on 2018/2/27.
 */

public abstract class BottomItemDelegate extends LatteDelegate {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
            TOUCH_TIME = 0;
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出应用", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
