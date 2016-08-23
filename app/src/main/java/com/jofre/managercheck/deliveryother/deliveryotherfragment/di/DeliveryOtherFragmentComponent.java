package com.jofre.managercheck.deliveryother.deliveryotherfragment.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragment;
import com.jofre.managercheck.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {DeliveryOtherFragmentModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface DeliveryOtherFragmentComponent {
    void inject(DeliveryOtherFragment fragment);
}
