package com.konifar.nice_button_sample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.konifar.nice_button_sample.R;
import com.konifar.nice_button_sample.databinding.ActivityMainBinding;
import com.konifar.nice_button_sample.fragment.CatsFragment;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getComponent().inject(this);
        initView();
    }

    private void initView() {
        setSupportActionBar(binding.toolbar);
        replaceFragment(CatsFragment.newInstance(), R.id.content_view);
    }

}
