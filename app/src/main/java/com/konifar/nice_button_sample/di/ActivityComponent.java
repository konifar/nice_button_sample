package com.konifar.nice_button_sample.di;

import com.konifar.nice_button_sample.activity.MainActivity;
import com.konifar.nice_button_sample.di.scope.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    FragmentComponent plus(FragmentModule module);

}
