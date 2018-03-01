package com.archer.android.ecapp.demo.generators;

import com.archer.lib.core.wechat.templates.WXEntryTemplate;
import com.example.annotations.EntryGenerator;

/**
 * Created by Archer on 2018/2/26.
 */
@EntryGenerator(
        packageName = "com.archer.android.ecapp.demo",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
