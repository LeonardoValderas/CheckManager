package com.jofre.managercheck.maturities.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.maturities.ui.MaturitiesActivity;
import com.jofre.managercheck.navigationmain.di.NavigationMainModule;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {MaturitiesModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface MaturitiesComponent {
    void inject(MaturitiesActivity activity);
}
