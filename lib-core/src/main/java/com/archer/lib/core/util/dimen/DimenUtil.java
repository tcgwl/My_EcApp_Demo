package com.archer.lib.core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.archer.lib.core.app.Latte;

/**
 * Created by Archer on 2018/2/7.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight() {
        Resources resources = Latte.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
