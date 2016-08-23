package com.jofre.managercheck.deliveryown.deliveryownfragmentlist.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.DeliveryOwnFragmentListFragment;
import com.jofre.managercheck.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {DeliveryOwnFragmentListModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface DeliveryOwnFragmentListComponent {
    void inject(DeliveryOwnFragmentListFragment fragment);
}
