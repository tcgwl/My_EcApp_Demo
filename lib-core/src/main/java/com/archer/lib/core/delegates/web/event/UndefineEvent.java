package com.archer.lib.core.delegates.web.event;

import com.archer.lib.core.util.log.LatteLogger;

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent", params);
        return null;
    }
}
