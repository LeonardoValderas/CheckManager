package com.jofre.managercheck;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class ManagerCheckAppModule {
    Application application;
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";

    public ManagerCheckAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }
}