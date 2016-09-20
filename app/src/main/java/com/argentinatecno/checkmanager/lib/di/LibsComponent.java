package com.argentinatecno.checkmanager.lib.di;


import com.argentinatecno.checkmanager.CheckManagerAppModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, CheckManagerAppModule.class})
public interface LibsComponent {
}