package com.archer.android.ecapp.demo;

import android.app.Application;

import com.archer.android.ecapp.demo.event.TestEvent;
import com.archer.lib.core.app.Latte;
import com.archer.lib.core.net.interceptors.DebugInterceptor;
import com.archer.lib.core.net.rx.AddCookieInterceptor;
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
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())
                //添加Cookie同步拦截器
                .withWebHost("https://www.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .withInterceptor(new DebugInterceptor("user_profile.php", R.raw.user_profile))
                .withInterceptor(new DebugInterceptor("index.php", R.raw.index_data))
                .withInterceptor(new DebugInterceptor("sort_list.php", R.raw.sort_list_data))
                .withInterceptor(new DebugInterceptor("sort_content_list.php", R.raw.sort_content_data_1))
                .withInterceptor(new DebugInterceptor("shop_cart.php", R.raw.shop_cart_data))
                .withInterceptor(new DebugInterceptor("shop_cart_count.php", R.raw.shop_cart_data))
                .withInterceptor(new DebugInterceptor("order_list.php", R.raw.order_list))
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
