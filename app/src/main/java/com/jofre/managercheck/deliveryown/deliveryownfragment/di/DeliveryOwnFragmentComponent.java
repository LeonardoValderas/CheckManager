package com.jofre.managercheck.deliveryown.deliveryownfragment.di;

import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentPresenter;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 5/7/2016.
 */
@Singleton
@Component(modules = {DeliveryOwnFragmentModule.class,LibsModule.class})
public interface DeliveryOwnFragmentComponent {
//void inject(ReceiverAddFragment fragment);
ImageLoader getImageLoader();
DeliveryOwnFragmentPresenter getPresenter();
}
