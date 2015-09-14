package com.frankzhu.recyclerviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.fragment.AnimFragment;
import com.frankzhu.recyclerviewdemo.fragment.FullyExpandedFragment;
import com.frankzhu.recyclerviewdemo.fragment.MultipleFragment;
import com.frankzhu.recyclerviewdemo.fragment.MultipleHeaderBottomFragment;
import com.frankzhu.recyclerviewdemo.fragment.NormalFragment;

import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int index = getIntent().getIntExtra("position", 0);
        Log.d("NormalTextViewHolder", "onClick--> index = " + index);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        updateFragment(index);
    }

    private void updateFragment(int index) {
        if (index == 0) {
            updateNormalFragment(NormalFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 1) {
            updateNormalFragment(NormalFragment.TYPE_GRID_LAYOUT);
        } else if (index == 2) {
            updateNormalFragment(NormalFragment.TYPE_STAGGERED_GRID_LAYOUT);
        } else if (index == 3) {
            updateMultipleFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 4) {
            updateMultipleFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 5) {
            updateMultipleHeaderFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 6) {
            updateMultipleHeaderFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 7) {
            updateAnimFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 8) {
            updateAnimFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        } else if (index == 9) {
            updateFullyExpandedFragment(MultipleFragment.TYPE_LINEAR_LAYOUT);
        } else if (index == 10) {
            updateFullyExpandedFragment(MultipleFragment.TYPE_GRID_LAYOUT);
        }
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

    public void updateAnimFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, AnimFragment.newInstance(type))
                .commit();
    }

    public void updateFullyExpandedFragment(int type) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FullyExpandedFragment.newInstance(type))
                .commit();
    }
}
