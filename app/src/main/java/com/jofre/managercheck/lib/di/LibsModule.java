package com.jofre.managercheck.lib.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.lib.GreenRobotEventBus;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class LibsModule {
    private Fragment fragment;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

//    @Provides
//    @Singleton
//    ImageStorage providesImageStorage(Context context, EventBus eventBus) {
//        ImageStorage imageStorage = new CloudinaryImageStorage(context, eventBus);
//        return imageStorage;
//    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }
}