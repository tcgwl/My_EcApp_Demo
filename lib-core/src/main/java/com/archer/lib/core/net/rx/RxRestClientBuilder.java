package com.archer.lib.core.net.rx;

import android.content.Context;

import com.archer.lib.core.net.RestCreator;
import com.archer.lib.core.ui.loader.LatteLoader;
import com.archer.lib.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Archer on 2018/2/7.
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    /**
     * 只允许同包内的类创建
     */
    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder parmas(WeakHashMap<String, Object> parmas) {
        PARAMS.putAll(parmas);
        return this;
    }

    public final RxRestClientBuilder parmas(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LatteLoader.DEFAULT_LOADER_STYLE;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS,
                mBody, mFile, mContext,
                mLoaderStyle);
    }
}
