package com.jofre.managercheck;

import android.app.Application;

import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiveradd.di.DaggerReceiverAddComponent;
import com.jofre.managercheck.receiveradd.di.ReceiverAddComponent;
import com.jofre.managercheck.receiveradd.di.ReceiverAddModule;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddView;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by LEO on 3/7/2016.
 */
public class ManagerCheckApp extends Application{
    private LibsModule libsModule;
    private ManagerCheckAppModule managerCheckAppModule;
    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
        initModules();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initModules() {
        libsModule = new LibsModule();
        managerCheckAppModule = new ManagerCheckAppModule(this);
    }

    public ReceiverAddComponent getReceiverAddComponent(ReceiverAddView view) {
        return DaggerReceiverAddComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(libsModule)
                .receiverAddModule(new ReceiverAddModule(view))
                .build();

        //return  null;

    }
}
