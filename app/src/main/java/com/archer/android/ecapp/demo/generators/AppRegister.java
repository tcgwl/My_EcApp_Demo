package com.archer.android.ecapp.demo.generators;

import com.archer.lib.core.wechat.templates.AppRegisterTemplate;
import com.example.annotations.AppRegisterGenerator;

/**
 * Created by Archer on 2018/2/26.
 */
@AppRegisterGenerator(
        packageName = "com.archer.android.ecapp.demo",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
