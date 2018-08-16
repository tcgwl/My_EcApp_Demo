package com.archer.lib.ec.main.sort.content;

import android.support.v7.widget.AppCompatImageView;

import com.archer.lib.ec.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    /**
     * 头数据
     */
    @Override
    protected void convertHead(BaseViewHolder holder, SectionBean item) {
        holder.setText(R.id.header, item.header);
        holder.setVisible(R.id.more, item.isMore());
        holder.addOnClickListener(R.id.more);
    }

    /**
     * content
     */
    @Override
    protected void convert(BaseViewHolder holder, SectionBean item) {
        //item.t返回SectionContentItemEntity类型
        final SectionContentItemEntity entity = item.t;
        final String name = entity.getGoodsName();
        final String thumb = entity.getGoodsThumb();
        final int goodsId = entity.getGoodsId();
        holder.setText(R.id.tv, name);
        final AppCompatImageView goodsImageView = holder.getView(R.id.iv);
        Glide.with(mContext)
                .load(thumb)
                .apply(OPTIONS)
                .into(goodsImageView);
    }
}
