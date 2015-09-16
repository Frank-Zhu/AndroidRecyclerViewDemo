package com.frankzhu.recyclerviewdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.adapter.DefaultMultipleAdapter;
import com.frankzhu.recyclerviewdemo.adapter.MultiSettingSelectAdapter;
import com.frankzhu.recyclerviewdemo.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      15/9/15 上午12:10
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 15/9/15      ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class MultipleSelectFragment extends Fragment {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private DefaultMultipleAdapter mDefaultMultipleAdapter;

    public MultipleSelectFragment() {

    }

    public static MultipleSelectFragment newInstance() {
        return new MultipleSelectFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_normal, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//这里用线性显示 类似于list view
        mDefaultMultipleAdapter = new DefaultMultipleAdapter(getActivity());
        ArrayList<String> items = new ArrayList<>();
        Collections.addAll(items, getActivity().getResources().getStringArray(R.array.titles));
        mDefaultMultipleAdapter.addItems(items);
        mRecyclerView.setAdapter(mDefaultMultipleAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mDefaultMultipleAdapter.setOnActionModeCallBack(new MultiSettingSelectAdapter.OnActionModeCallBack() {
            @Override
            public void showActionMode() {
                mDefaultMultipleAdapter.setIsActionModeShow(true);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                activity.startSupportActionMode(mDeleteMode);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private ActionMode.Callback mDeleteMode = new ActionMode.Callback() {
        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mDefaultMultipleAdapter.setIsActionModeShow(false);
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            getActivity().getMenuInflater().inflate(R.menu.menu_delete, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_delete:
                    onDeleteItems();
                    actionMode.finish();
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    private void onDeleteItems() {
        ArrayList<String> deleteItems = new ArrayList<>();
        for (Integer integer : mDefaultMultipleAdapter.getMultiSelectPositions()) {
            deleteItems.add(mDefaultMultipleAdapter.getItemData(integer));
            mDefaultMultipleAdapter.notifyItemRemoved(integer);
        }
        if (deleteItems.size() > 0) {
            mDefaultMultipleAdapter.getDataList().removeAll(deleteItems);
        }
    }
}
