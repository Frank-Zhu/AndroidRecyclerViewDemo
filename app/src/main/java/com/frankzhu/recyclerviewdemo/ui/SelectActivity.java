package com.frankzhu.recyclerviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.frankzhu.recyclerviewdemo.R;
import com.frankzhu.recyclerviewdemo.fragment.MultipleSelectFragment;
import com.frankzhu.recyclerviewdemo.fragment.SingleSelectFragment;

import butterknife.ButterKnife;


public class SelectActivity extends AppCompatActivity {
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
        if (index == 11) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SingleSelectFragment.newInstance())
                    .commit();
        } else if (index == 12) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MultipleSelectFragment.newInstance())
                    .commit();
        }
    }
}
