package com.jofre.managercheck.lib.di;

import com.jofre.managercheck.ManagerCheckAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LEO on 5/7/2016.
 */
@Singleton
@Component(modules = {LibsModule.class, ManagerCheckAppModule.class})
public interface LibsComponent {
}