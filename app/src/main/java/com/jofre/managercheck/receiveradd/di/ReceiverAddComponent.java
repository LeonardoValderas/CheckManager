package com.jofre.managercheck.receiveradd.di;

import com.jofre.managercheck.ManagerCheckAppModule;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 5/7/2016.
 */
@Singleton
@Component(modules = {ReceiverAddModule.class,LibsModule.class, ManagerCheckAppModule.class})
public interface ReceiverAddComponent {
void inject(ReceiverAddFragment fragment);
}
