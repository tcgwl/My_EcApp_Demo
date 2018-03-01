package com.archer.android.ecapp.demo;

import android.app.Application;

import com.archer.lib.core.app.Latte;
import com.archer.lib.core.net.interceptors.DebugInterceptor;
import com.archer.lib.ec.database.DatabaseManager;
import com.archer.lib.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Archer on 2018/2/6.
 */

public class DemoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://192.168.1.145:8080/RestServer/api/")
                .withWeChatAppId("你的微信AppKey")
                .withWeChatAppSecret("你的微信AppSecret")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();
//        initStetho();
        DatabaseManager.getInstance().init(this);
    }

//    private void initStetho() {
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                .build()
//        );
//    }
}
