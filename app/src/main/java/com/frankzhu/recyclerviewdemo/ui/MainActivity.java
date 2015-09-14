package com.frankzhu.recyclerviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.fragment.ItemsFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ItemsFragment.newInstance())
                    .commit();
        }
    }
}
