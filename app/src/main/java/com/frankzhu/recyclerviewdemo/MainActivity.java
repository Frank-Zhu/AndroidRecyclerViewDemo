package com.frankzhu.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.frankzhu.recyclerviewdemo.fragment.MultipleFragment;
import com.frankzhu.recyclerviewdemo.fragment.MultipleHeaderBottomFragment;
import com.frankzhu.recyclerviewdemo.fragment.NormalFragment;

import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            updateNormalFragment(NormalFragment.TYPE_LINEAR_LAYOUT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_normal_list) {
            updateNormalFragment(NormalFragment.TYPE_LINEAR_LAYOUT);
            return true;
        } else if (id == R.id.action_normal_grid) {
            updateNormalFragment(NormalFragment.TYPE_GRID_LAYOUT);
            return true;
        } else if (id == R.id.action_normal_staggered) {
            updateNormalFragment(NormalFragment.TYPE_STAGGERED_GRID_LAYOUT);
            return true;
        } else if (id == R.id.action_multiple_list) {
            updateMultipleFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
            return true;
        } else if (id == R.id.action_multiple_grid) {
            updateMultipleHeaderFragment(MultipleFragment.TYPE_GRID_LAYOUT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateNormalFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, NormalFragment.newInstance(type))
                .commit();
    }

    public void updateMultipleFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MultipleFragment.newInstance(type))
                .commit();
    }

    public void updateMultipleHeaderFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, MultipleHeaderBottomFragment.newInstance(type))
                .commit();
    }
}
