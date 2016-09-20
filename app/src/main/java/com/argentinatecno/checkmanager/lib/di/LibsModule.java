package com.argentinatecno.checkmanager.lib.di;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {
    private Fragment fragment;
    private Activity activity;

    public LibsModule() {
    }

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader() {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (fragment != null)
            imageLoader.setLoaderContext(fragment);
        else
            imageLoader.setLoaderContext(activity);

        return imageLoader;
    }
//    @Provides
//    @Singleton
//    ImageLoader providesImageLoaderA(Activity activity) {
//        GlideImageLoader imageLoader = new GlideImageLoader();
//        if (activity != null)
//            imageLoader.setLoaderContext(activity);
//        return imageLoader;
//    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    Activity providesActivity() {
        return this.activity;
    }
}