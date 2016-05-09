package com.konifar.nice_button_sample.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.konifar.nice_button_sample.R;
import com.konifar.nice_button_sample.databinding.ViewListLoadingBinding;

public class ListLoadingView extends FrameLayout {

    private ViewListLoadingBinding binding;

    public ListLoadingView(Context context) {
        this(context, null);
    }

    public ListLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_list_loading, this, true);
    }

    public void switchVisible(boolean visible) {
        int visibility = visible ? View.VISIBLE : View.GONE;
        binding.listLoading.setVisibility(visibility);
    }

}
