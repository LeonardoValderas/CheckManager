package com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.DeliveryOtherFragmentListFragment;
import com.jofre.managercheck.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {DeliveryOtherFragmentListModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface DeliveryOtherFragmentListComponent {
    void inject(DeliveryOtherFragmentListFragment fragment);
}
