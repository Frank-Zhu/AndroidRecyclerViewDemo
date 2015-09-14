package com.frankzhu.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frankzhu.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/1/15  18:18.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/1/15        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class AnimAdapter extends RecyclerView.Adapter<AnimAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private ArrayList<String> mTitles = new ArrayList<>();

    public AnimAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void addTitles(String[] titles) {
        Collections.addAll(mTitles, titles);
        notifyItemRangeInserted(0, titles.length);
    }

    public void addTitle(String title) {
        mTitles.add(1, title);
        notifyItemInserted(1);
    }

    public void remove(int position) {
        mTitles.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false), this);
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_view)
        TextView mTextView;
        AnimAdapter mAdapter;

        NormalTextViewHolder(View view, AnimAdapter adapter) {
            super(view);
            mAdapter = adapter;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("NormalTextViewHolder", "onClick--> position = " + getPosition());
            if (getPosition() == 2) {
                mAdapter.remove(getPosition());
            } else {
                mAdapter.addTitle("test" + getPosition());
            }
        }
    }
}
