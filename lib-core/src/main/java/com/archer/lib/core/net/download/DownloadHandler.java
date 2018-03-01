package com.archer.lib.core.net.download;

import android.os.AsyncTask;

import com.archer.lib.core.net.RestCreator;
import com.archer.lib.core.net.callback.IError;
import com.archer.lib.core.net.callback.IFailure;
import com.archer.lib.core.net.callback.IRequest;
import com.archer.lib.core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Archer on 2018/2/7.
 */

public final class DownloadHandler {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;

    public DownloadHandler(String url,
                           IRequest request,
                           String download_dir,
                           String extension,
                           String name,
                           ISuccess success,
                           IError error,
                           IFailure failure) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = download_dir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.ERROR = error;
        this.FAILURE = failure;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, responseBody, NAME);
                    //这里一定要注意判断，否则文件下载不全
                    if (task.isCancelled()) {
                        REQUEST.onRequestEnd();
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }
                RestCreator.getParams().clear();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (FAILURE != null) {
                    FAILURE.onFailure();
                }
                RestCreator.getParams().clear();
            }
        });


    }
}
