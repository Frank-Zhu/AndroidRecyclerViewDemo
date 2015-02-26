package com.frankzhu.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.frankzhu.recyclerviewdemo.fragment.ItemsFragment;

import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ItemsFragment.newInstance())
                    .commit();
        }
    }
}
