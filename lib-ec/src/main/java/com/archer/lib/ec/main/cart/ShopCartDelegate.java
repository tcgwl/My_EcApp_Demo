package com.archer.lib.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.archer.lib.core.delegates.bottom.BottomItemDelegate;
import com.archer.lib.ec.R;

/**
 * Created by Archer on 2018/2/27.
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
