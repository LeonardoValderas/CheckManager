package com.argentinatecno.checkmanager.main.activity_maturities.di;

import com.argentinatecno.checkmanager.CheckManagerAppModule;
import com.argentinatecno.checkmanager.lib.di.LibsModule;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {MaturitiesModule.class, LibsModule.class, CheckManagerAppModule.class})
public interface MaturitiesComponent {
    void inject(MaturitiesActivity activity);
}
