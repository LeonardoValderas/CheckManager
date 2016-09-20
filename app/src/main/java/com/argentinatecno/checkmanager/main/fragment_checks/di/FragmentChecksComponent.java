package com.argentinatecno.checkmanager.main.fragment_checks.di;

import com.argentinatecno.checkmanager.CheckManagerAppModule;
import com.argentinatecno.checkmanager.lib.di.LibsModule;
import com.argentinatecno.checkmanager.main.fragment_checks.ui.FragmentChecks;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FragmentChecksModule.class, LibsModule.class, CheckManagerAppModule.class})
public interface FragmentChecksComponent {
    void inject(FragmentChecks fragment);
}
