package com.archer.lib.ec.main.index;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

/**
 * Created by Archer on 2018/2/28.
 */

@SuppressWarnings("unused")
public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {
    //顶部距离
    private int mDistanceY = 0;
    //颜色变化速度
    private static final int SHOW_SPEED = 3;

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
