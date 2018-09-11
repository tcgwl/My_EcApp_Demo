package com.archer.lib.ec.main.personal.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.archer.lib.core.delegates.LatteDelegate;
import com.archer.lib.ec.R;

/**
 * Created by 傅令杰
 */

public class OrderCommentDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
    }
}
