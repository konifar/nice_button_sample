package com.konifar.nice_button_sample;

import android.app.Application;
import android.support.annotation.NonNull;

import com.konifar.nice_button_sample.di.AppComponent;
import com.konifar.nice_button_sample.di.AppModule;
import com.konifar.nice_button_sample.di.DaggerAppComponent;

public class MainApplication extends Application {

    AppComponent appComponent;

    @NonNull
    public AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        new StethoWrapper(this).setup();
    }

}
