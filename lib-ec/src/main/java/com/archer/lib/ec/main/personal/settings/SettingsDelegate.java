package com.archer.lib.ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.archer.lib.core.delegates.LatteDelegate;
import com.archer.lib.ec.R;

public class SettingsDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
