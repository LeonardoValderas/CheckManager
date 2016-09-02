package com.jofre.managercheck.receiver.receiverlistfragment.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.ReceiverListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 8/7/2016.
 */
@Singleton
@Component(modules = {ReceiverListModule.class, LibsModule.class, ManagerCheckAppModule.class})
public interface ReceiverListComponent {
    void inject(ReceiverListFragment fragment);
}
