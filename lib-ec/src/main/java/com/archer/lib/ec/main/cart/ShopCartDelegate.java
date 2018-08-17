package com.archer.lib.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.archer.lib.core.delegates.bottom.BottomItemDelegate;
import com.archer.lib.core.net.RestClient;
import com.archer.lib.core.net.callback.ISuccess;
import com.archer.lib.core.ui.recycler.MultipleItemEntity;
import com.archer.lib.ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by Archer on 2018/2/27.
 */

public class ShopCartDelegate extends BottomItemDelegate implements ISuccess, View.OnClickListener {
    private ShopCartAdapter mAdapter = null;
    //购物车数量标记
    private int mCurrentCount = 0;
    private int mTotalCount = 0;
    private double mTotalPrice = 0.00;

    private RecyclerView mRecyclerView = null;
    private IconTextView mIconSelectAll = null;
    private ViewStubCompat mStubNoItem = null;
    private AppCompatTextView mTvTotalPrice = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRecyclerView = $(R.id.rv_shop_cart);
        mIconSelectAll = $(R.id.icon_shop_cart_select_all);
        mStubNoItem = $(R.id.stub_no_item);
        mTvTotalPrice = $(R.id.tv_shop_cart_total_price);
        mIconSelectAll.setTag(0);
        $(R.id.icon_shop_cart_select_all).setOnClickListener(this);
        $(R.id.tv_top_shop_cart_remove_selected).setOnClickListener(this);
        $(R.id.tv_top_shop_cart_clear).setOnClickListener(this);
        $(R.id.tv_shop_cart_pay).setOnClickListener(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("shop_cart.php")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void onSuccess(String response) {
        final ArrayList<MultipleItemEntity> datas =
                new ShopCartDataConverter()
                        .setJsonData(response)
                        .convert();
        mAdapter = new ShopCartAdapter(datas);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
