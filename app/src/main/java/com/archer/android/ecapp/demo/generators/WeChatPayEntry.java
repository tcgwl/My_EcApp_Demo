package com.archer.android.ecapp.demo.generators;

import com.archer.lib.core.wechat.templates.WXPayEntryTemplate;
import com.example.annotations.PayEntryGenerator;

/**
 * Created by Archer on 2018/2/26.
 */
@PayEntryGenerator(
        packageName = "com.archer.android.ecapp.demo",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
