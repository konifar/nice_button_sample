package com.konifar.nice_button_sample.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.konifar.nice_button_sample.MainApplication;
import com.konifar.nice_button_sample.di.ActivityComponent;
import com.konifar.nice_button_sample.di.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    @NonNull
    public ActivityComponent getComponent() {
        if (activityComponent == null) {
            MainApplication mainApplication = (MainApplication) getApplication();
            activityComponent = mainApplication.getComponent().plus(new ActivityModule(this));
        }
        return activityComponent;
    }
}
