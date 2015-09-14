package com.frankzhu.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Date:      2015/2/6  14:34.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/6        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class HeaderBottomItemAdapter extends BaseMultipleItemAdapter {
    private String[] mTitles;

    public HeaderBottomItemAdapter(Context context) {
        super(context);
        mTitles = context.getResources().getStringArray(R.array.titles);
        mHeaderCount = 1;
        mBottomCount = 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).mTextView.setText(mTitles[0]);
        } else if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).mTextView.setText(mTitles[position - mHeaderCount]);
        } else if (holder instanceof BottomViewHolder) {
            ((BottomViewHolder) holder).mTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderView(ViewGroup parent) {
        return new HeaderViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentView(ViewGroup parent) {
        return new ContentViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateBottomView(ViewGroup parent) {
        return new BottomViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
    }

    @Override
    public int getContentItemCount() {
        return 4;
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("TextViewHolder", "onClick--> position = " + getPosition());
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;
        @Bind(R.id.image_view)
        ImageView mImageView;

        HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;
        @Bind(R.id.image_view)
        ImageView mImageView;

        BottomViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
        }
    }
}
