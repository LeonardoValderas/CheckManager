package com.jofre.managercheck.receiveradd.di;

import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiveradd.ReceiverAddPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 5/7/2016.
 */
@Singleton
//@Component(modules = {ReceiverAddModule.class,LibsModule.class, ManagerCheckAppModule.class})
@Component(modules = {ReceiverAddModule.class,LibsModule.class})
public interface ReceiverAddComponent {
//void inject(ReceiverAddFragment fragment);
ImageLoader getImageLoader();
ReceiverAddPresenter getPresenter();
}
