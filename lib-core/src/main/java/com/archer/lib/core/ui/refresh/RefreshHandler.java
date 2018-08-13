package com.archer.lib.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.archer.lib.core.app.Latte;
import com.archer.lib.core.net.RestClient;
import com.archer.lib.core.net.callback.IError;
import com.archer.lib.core.net.callback.IFailure;
import com.archer.lib.core.net.callback.ISuccess;
import com.archer.lib.core.util.log.LatteLogger;
import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * 刷新小助手
 * Created by Archer on 2018/2/28.
 */

public class RefreshHandler implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = RefreshHandler.class.getSimpleName();
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout layout) {
        REFRESH_LAYOUT = layout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行一些网络请求
                REFRESH_LAYOUT.setRefreshing(false);
            }
        }, 2000);
    }

    public void firstPage(String url) {
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        LatteLogger.json(TAG, response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LatteLogger.json(TAG, "error: " + msg);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        LatteLogger.json(TAG, "failure");
                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onLoadMoreRequested() {

    }
}
