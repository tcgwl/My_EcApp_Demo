package com.archer.lib.core.net;

import android.content.Context;

import com.archer.lib.core.net.callback.IError;
import com.archer.lib.core.net.callback.IFailure;
import com.archer.lib.core.net.callback.IRequest;
import com.archer.lib.core.net.callback.ISuccess;
import com.archer.lib.core.ui.loader.LatteLoader;
import com.archer.lib.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Archer on 2018/2/7.
 */

public class RestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    /**
     * 只允许同包内的类创建
     */
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder parmas(WeakHashMap<String, Object> parmas) {
        PARAMS.putAll(parmas);
        return this;
    }

    public final RestClientBuilder parmas(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RestClientBuilder dir(String downloadDir) {
        this.mDownloadDir = downloadDir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_STYLE;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mName, mIRequest, mISuccess, mIFailure, mIError, mBody, mFile, mContext, mLoaderStyle);
    }
}
