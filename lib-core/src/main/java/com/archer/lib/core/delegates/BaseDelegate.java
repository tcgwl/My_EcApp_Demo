package com.archer.lib.core.delegates;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archer.lib.core.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Archer on 2018/2/6.
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    private View mRootView = null;
    private Unbinder mUnbinder;
    public abstract Object setLayout();
    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    public <T extends View> T $(@IdRes int viewId) {
        if (mRootView != null) {
            return (T) mRootView.findViewById(viewId);
        }
        throw new NullPointerException("rootView is null");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or View!");
        }
        if (rootView != null) {
            mRootView = rootView;
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) _mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
