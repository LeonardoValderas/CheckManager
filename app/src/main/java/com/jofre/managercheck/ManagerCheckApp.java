package com.jofre.managercheck;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.receiveradd.di.DaggerReceiverAddComponent;
import com.jofre.managercheck.receiveradd.di.ReceiverAddComponent;
import com.jofre.managercheck.receiveradd.di.ReceiverAddModule;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddView;
import com.jofre.managercheck.receiveraddlist.di.DaggerReceiverAddListComponent;
import com.jofre.managercheck.receiveraddlist.di.ReceiverAddListComponent;
import com.jofre.managercheck.receiveraddlist.di.ReceiverAddListModule;
import com.jofre.managercheck.receiveraddlist.ui.ReceiverAddListView;
import com.jofre.managercheck.receiveraddlist.ui.adapters.OnItemClickListener;
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
//        initDB();
        initModules();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
  //      DBTearDown();
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

    public ReceiverAddComponent getReceiverAddComponent(ReceiverAddView view, Fragment fragment) {
        return DaggerReceiverAddComponent
                .builder()
          //      .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .receiverAddModule(new ReceiverAddModule(view))
                .build();

        //return  null;

    }

    public ReceiverAddListComponent getReceiverAddListComponent(ReceiverAddListView view, Fragment fragment, OnItemClickListener onItemClickListener, Context context) {
        return DaggerReceiverAddListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .receiverAddListModule(new ReceiverAddListModule(view, onItemClickListener,context))
                .build();

//        return  null;

    }
}
