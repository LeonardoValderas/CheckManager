package com.argentinatecno.checkmanager.main.fragment_add.di;

import com.argentinatecno.checkmanager.CheckManagerAppModule;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.lib.di.LibsModule;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddPresenter;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAdd;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {FragmentAddModule.class, LibsModule.class, CheckManagerAppModule.class})
public interface FragmentAddComponent {
    void inject(FragmentAdd fragment);
    ImageLoader getImageLoader();
    FragmentAddPresenter getPresenter();
}
