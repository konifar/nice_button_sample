package com.konifar.nice_button_sample.di;

import com.konifar.nice_button_sample.StethoWrapper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(StethoWrapper stethoDelegator);

    ActivityComponent plus(ActivityModule module);

}
