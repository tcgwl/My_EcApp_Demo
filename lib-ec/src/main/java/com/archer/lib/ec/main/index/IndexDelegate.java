package com.archer.lib.ec.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.archer.lib.core.delegates.bottom.BottomItemDelegate;
import com.archer.lib.core.ui.recycler.BaseDecoration;
import com.archer.lib.core.ui.refresh.RefreshHandler;
import com.archer.lib.ec.R;
import com.archer.lib.ec.main.EcBottomDelegate;
import com.joanzapata.iconify.widget.IconTextView;

/**
 * Created by Archer on 2018/2/27.
 */

public class IndexDelegate extends BottomItemDelegate {
    RecyclerView mRecyclerView = null;
    SwipeRefreshLayout mRefreshLayout = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRecyclerView = $(R.id.rv_index);
        mRefreshLayout = $(R.id.srl_index);
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView, new IndexDataConverter());
    }

    private void initRefreshLayout() {
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
        mRecyclerView.setLayoutManager(manager);
        final Context context = getContext();
        if (context != null) {
            mRecyclerView.addItemDecoration
                    (BaseDecoration.create(ContextCompat.getColor(context, R.color.app_background), 5));
        }
        final EcBottomDelegate ecBottomDelegate = getParentDelegate();
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create(ecBottomDelegate));
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mRefreshHandler.firstPage("index.php");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

}
