package com.archer.android.ecapp.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.archer.lib.core.app.Latte;
import com.archer.lib.core.delegates.LatteDelegate;
import com.archer.lib.core.net.RestClient;
import com.archer.lib.core.net.callback.IError;
import com.archer.lib.core.net.callback.IFailure;
import com.archer.lib.core.net.callback.ISuccess;

/**
 * Created by Archer on 2018/2/6.
 */

public class DemoDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_demo;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("TEST", "success: " + response);
                        Toast.makeText(Latte.getApplicationContext(), "success:\n" + response, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(Latte.getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(Latte.getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
