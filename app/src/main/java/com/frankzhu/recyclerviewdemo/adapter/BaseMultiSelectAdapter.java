package com.frankzhu.recyclerviewdemo.adapter;

import android.content.Context;

import java.util.ArrayList;

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
public abstract class BaseMultiSelectAdapter<T> extends BaseRecyclerAdapter<T> {
    protected ArrayList<Integer> mMultiSelectPositions = new ArrayList<>();
    protected boolean isSelectedEnable = true;

    public BaseMultiSelectAdapter(Context context) {
        super(context);
    }

    public ArrayList<Integer> getMultiSelectPositions() {
        return mMultiSelectPositions;
    }

    public void setIsSelectedEnable(boolean isSelectedEnable) {
        this.isSelectedEnable = isSelectedEnable;
    }

    /**
     * 是否有选中数据
     *
     * @return true:有   false:没有
     */
    public boolean isSelectedData() {
        return mMultiSelectPositions.size() > 0;
    }

    /**
     * 添加选中项
     *
     * @param positions 选中项数据
     */
    public void addSelectPositions(ArrayList<Integer> positions) {
        if (positions != null && positions.size() > 0) {
            mMultiSelectPositions.addAll(positions);
            notifyItemRangeChanged(0, positions.size());
        }
    }

    /**
     * 添加选中项
     *
     * @param data 选中项数据
     */
    public void addSelectPosition(Integer data) {
        if (!mMultiSelectPositions.contains(data)) {
            mMultiSelectPositions.add(data);
        }
    }

    /**
     * 删除选中项
     *
     * @param data 选中项数据
     */
    public void removeSelectPosition(Integer data) {
        if (mMultiSelectPositions.contains(data)) {
            mMultiSelectPositions.remove(data);
        }
    }

    /**
     * 清除所有选中项
     */
    public void clearAllSelect() {
        mMultiSelectPositions.clear();
        notifyDataSetChanged();
    }

    /**
     * 选中所有选择项
     */
    public void selectAllPositions() {
        mMultiSelectPositions.clear();
        for (int i = 0; i < getItemCount(); i++) {
            mMultiSelectPositions.add(i);
        }
        notifyDataSetChanged();
    }

    /**
     * 是否选中项
     *
     * @param data 数据
     * @return true:是   false:不是
     */
    public boolean isSelected(Integer data) {
        return mMultiSelectPositions.contains(data);
    }
}
