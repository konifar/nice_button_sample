package com.konifar.nice_button_sample.di;

import com.konifar.nice_button_sample.di.scope.FragmentScope;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}
