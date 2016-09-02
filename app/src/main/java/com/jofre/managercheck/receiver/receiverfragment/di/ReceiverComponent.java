package com.jofre.managercheck.receiver.receiverfragment.di;

import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 5/7/2016.
 */
@Singleton
//@Component(modules = {ReceiverModule.class,LibsModule.class, ManagerCheckAppModule.class})
@Component(modules = {ReceiverModule.class,LibsModule.class})
public interface ReceiverComponent {
//void inject(ReceiverFragment fragment);
ImageLoader getImageLoader();
ReceiverFragmentPresenter getPresenter();
}
