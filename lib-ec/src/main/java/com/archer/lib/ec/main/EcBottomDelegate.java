package com.archer.lib.ec.main;

import android.graphics.Color;

import com.archer.lib.core.delegates.bottom.BaseBottomDelegate;
import com.archer.lib.core.delegates.bottom.BottomItemDelegate;
import com.archer.lib.core.delegates.bottom.BottomTabBean;
import com.archer.lib.core.delegates.bottom.ItemBuilder;
import com.archer.lib.ec.main.cart.ShopCartDelegate;
import com.archer.lib.ec.main.discover.DiscoverDelegate;
import com.archer.lib.ec.main.index.IndexDelegate;
import com.archer.lib.ec.main.personal.PersonalDelegate;
import com.archer.lib.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by Archer on 2018/2/27.
 */

public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopCartDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
