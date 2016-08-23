package com.jofre.managercheck.receiveradd.receiveraddlistfragment.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.ReceiverAddListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {ReceiverAddListModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface ReceiverAddListComponent {
    void inject(ReceiverAddListFragment fragment);
}
