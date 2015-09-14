package com.frankzhu.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frankzhu.recyclerviewdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/8/15  17:33.
 * Description: 多选适配器
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/8/15        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public abstract class MultiSettingSelectAdapter<T> extends BaseMultiSelectAdapter<T> {
    public MultiSettingSelectAdapter(Context context) {
        super(context);
    }

    public abstract String getItemTitle(int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultiSettingSelectViewHolder(mLayoutInflater.inflate(R.layout.item_setting_select, parent, false), this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MultiSettingSelectViewHolder) {
            ((MultiSettingSelectViewHolder) holder).bindViewData(getItemTitle(position), position);
        }
    }

    static class MultiSettingSelectViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView mTvName;
        @Bind(R.id.iv_check)
        ImageView mIvCheck;

        MultiSettingSelectAdapter mAdapter;

        MultiSettingSelectViewHolder(View view, MultiSettingSelectAdapter adapter) {
            super(view);
            ButterKnife.bind(this, view);
            mAdapter = adapter;
        }

        public void bindViewData(String name, int position) {
            mIvCheck.setVisibility((mAdapter.isSelected(position)) ? View.VISIBLE : View.GONE);
            mTvName.setText(name);
        }

        @OnClick(R.id.fl_check)
        void onSelected() {
            if (mAdapter.isSelectedEnable) {
                if (mAdapter.isSelected(getPosition())) {//已选中
                    mAdapter.removeSelectPosition(getPosition());
                } else {//未选中
                    mAdapter.addSelectPosition(getPosition());
                }
                mAdapter.notifyItemChanged(getPosition());
            }
        }
    }
}
