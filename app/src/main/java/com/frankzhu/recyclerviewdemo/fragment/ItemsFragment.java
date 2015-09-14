package com.frankzhu.recyclerviewdemo.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.adapter.ItemsAdapter;
import com.frankzhu.recyclerviewdemo.bean.DemoItem;
import com.frankzhu.recyclerviewdemo.db.ItemsDataHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/25  18:03.
 * Description:
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/2/25        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class ItemsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ItemsDataHelper mDataHelper;
    private ItemsAdapter mAdapter;

    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataHelper = new ItemsDataHelper(getActivity());
    }

    private void loadItems() {
        String[] items = getActivity().getResources().getStringArray(R.array.items);
        ArrayList<DemoItem> demoItems = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            demoItems.add(new DemoItem(i, items[i]));
        }
        mDataHelper.bulkInsert(demoItems);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ItemsAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return mDataHelper.getCursorLoader();
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            loadItems();
        } else {
            mAdapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.changeCursor(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
