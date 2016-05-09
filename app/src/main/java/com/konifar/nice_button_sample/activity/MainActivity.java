package com.konifar.nice_button_sample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.konifar.nice_button_sample.R;
import com.konifar.nice_button_sample.databinding.ActivityMainBinding;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends BaseActivity {

    @Inject
    CompositeSubscription subscription;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getComponent().inject(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    private void initView() {
        setSupportActionBar(binding.toolbar);
    }

}
