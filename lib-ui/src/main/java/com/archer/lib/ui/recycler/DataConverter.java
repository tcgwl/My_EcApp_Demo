package com.archer.lib.ui.recycler;

import java.util.ArrayList;

/**
 * 数据转换的约束
 * Created by Archer on 2018/3/1.
 */

public abstract class DataConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String jsonData) {
        mJsonData = jsonData;
        return this;
    }

    public String getJsonData() {
        if (mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("DATA IS NULL!");
        }
        return mJsonData;
    }

    public void clearData() {
        ENTITIES.clear();
    }
}
