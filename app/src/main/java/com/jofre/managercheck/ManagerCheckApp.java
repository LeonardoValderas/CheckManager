package com.jofre.managercheck;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.deliveryother.deliveryotherfragment.di.DaggerDeliveryOtherFragmentComponent;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.di.DeliveryOtherFragmentComponent;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.di.DeliveryOtherFragmentModule;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragmentView;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.di.DaggerDeliveryOtherFragmentListComponent;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.di.DeliveryOtherFragmentListComponent;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.di.DeliveryOtherFragmentListModule;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.DeliveryOtherFragmentListView;
import com.jofre.managercheck.deliveryown.deliveryownfragment.di.DaggerDeliveryOwnFragmentComponent;
import com.jofre.managercheck.deliveryown.deliveryownfragment.di.DeliveryOwnFragmentComponent;
import com.jofre.managercheck.deliveryown.deliveryownfragment.di.DeliveryOwnFragmentModule;
import com.jofre.managercheck.deliveryown.deliveryownfragment.ui.DeliveryOwnFragmentView;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.di.DaggerDeliveryOwnFragmentListComponent;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.di.DeliveryOwnFragmentListComponent;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.di.DeliveryOwnFragmentListModule;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.DeliveryOwnFragmentListView;
import com.jofre.managercheck.lib.di.LibsModule;
import com.jofre.managercheck.navigationmain.di.DaggerNavigationMainComponent;
import com.jofre.managercheck.navigationmain.di.NavigationMainComponent;
import com.jofre.managercheck.navigationmain.di.NavigationMainModule;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivityView;
import com.jofre.managercheck.receiveradd.receiveraddfragment.di.DaggerReceiverAddComponent;
import com.jofre.managercheck.receiveradd.receiveraddfragment.di.ReceiverAddComponent;
import com.jofre.managercheck.receiveradd.receiveraddfragment.di.ReceiverAddModule;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ui.ReceiverAddView;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.di.DaggerReceiverAddListComponent;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.di.ReceiverAddListComponent;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.di.ReceiverAddListModule;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.ReceiverAddListView;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.adapters.OnItemClickListener;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by LEO on 3/7/2016.
 */
public class ManagerCheckApp extends Application {
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

    public NavigationMainComponent getNavigationMainComponent(NavigationMainActivityView view, Activity activity, Context context) {
        return DaggerNavigationMainComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(activity))
                .navigationMainModule(new NavigationMainModule(view,context,activity))
                .build();

//        return  null;

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
                .receiverAddListModule(new ReceiverAddListModule(view, onItemClickListener, context, fragment))
                .build();

//        return  null;

    }


    public DeliveryOwnFragmentComponent getDeliveryOwnFragmentComponent(DeliveryOwnFragmentView view, Fragment fragment) {
        return DaggerDeliveryOwnFragmentComponent
                .builder()
                //      .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOwnFragmentModule(new DeliveryOwnFragmentModule(view))
                .build();

        //return  null;

    }

    public DeliveryOwnFragmentListComponent getDeliveryOwnFragmentListComponent(DeliveryOwnFragmentListView view, Fragment fragment, com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOwnFragmentListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOwnFragmentListModule(new DeliveryOwnFragmentListModule(view, onItemClickListener, context, fragment))
                .build();

//        return  null;

    }

    public DeliveryOtherFragmentListComponent getDeliveryOtherFragmentListComponent(DeliveryOtherFragmentListView view, Fragment fragment, com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOtherFragmentListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOtherFragmentListModule(new DeliveryOtherFragmentListModule(view, onItemClickListener, context, fragment))
                .build();

//        return  null;

    }
    public DeliveryOtherFragmentComponent getDeliveryOtherFragmentComponent(DeliveryOtherFragmentView view, Fragment fragment, com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOtherFragmentComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOtherFragmentModule(new DeliveryOtherFragmentModule(view, onItemClickListener, context, fragment))
                .build();

//        return  null;

    }
}
