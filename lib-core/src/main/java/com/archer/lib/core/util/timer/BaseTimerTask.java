package com.archer.lib.core.util.timer;

import java.util.TimerTask;

/**
 * Created by Archer on 2018/2/9.
 */

public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener timerListener) {
        mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
