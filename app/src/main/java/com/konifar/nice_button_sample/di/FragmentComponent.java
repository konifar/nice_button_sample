package com.konifar.nice_button_sample.di;

import com.konifar.nice_button_sample.di.scope.FragmentScope;
import com.konifar.nice_button_sample.fragment.CatsFragment;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(CatsFragment fragment);

}
