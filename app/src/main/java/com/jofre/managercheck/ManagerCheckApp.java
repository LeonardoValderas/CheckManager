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
import com.jofre.managercheck.maturities.di.DaggerMaturitiesComponent;
import com.jofre.managercheck.maturities.di.MaturitiesComponent;
import com.jofre.managercheck.maturities.di.MaturitiesModule;
import com.jofre.managercheck.maturities.ui.MaturitiesView;
import com.jofre.managercheck.navigationmain.di.DaggerNavigationMainComponent;
import com.jofre.managercheck.navigationmain.di.NavigationMainComponent;
import com.jofre.managercheck.navigationmain.di.NavigationMainModule;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivityView;
import com.jofre.managercheck.receiver.receiverfragment.di.DaggerReceiverComponent;
import com.jofre.managercheck.receiver.receiverfragment.di.ReceiverComponent;
import com.jofre.managercheck.receiver.receiverfragment.di.ReceiverModule;
import com.jofre.managercheck.receiver.receiverfragment.ui.ReceiverView;
import com.jofre.managercheck.receiver.receiverlistfragment.di.DaggerReceiverListComponent;
import com.jofre.managercheck.receiver.receiverlistfragment.di.ReceiverListComponent;
import com.jofre.managercheck.receiver.receiverlistfragment.di.ReceiverListModule;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.ReceiverListView;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.adapters.OnItemClickListener;
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
    }

    public ReceiverComponent getReceiverAddComponent(ReceiverView view, Fragment fragment) {
        return DaggerReceiverComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .receiverModule(new ReceiverModule(view))
                .build();
    }

    public ReceiverListComponent getReceiverAddListComponent(ReceiverListView view, Fragment fragment, OnItemClickListener onItemClickListener, Context context) {
        return DaggerReceiverListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .receiverListModule(new ReceiverListModule(view, onItemClickListener, context, fragment))
                .build();
    }


    public DeliveryOwnFragmentComponent getDeliveryOwnFragmentComponent(DeliveryOwnFragmentView view, Fragment fragment) {
        return DaggerDeliveryOwnFragmentComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .deliveryOwnFragmentModule(new DeliveryOwnFragmentModule(view))
                .build();
    }

    public DeliveryOwnFragmentListComponent getDeliveryOwnFragmentListComponent(DeliveryOwnFragmentListView view, Fragment fragment, com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOwnFragmentListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOwnFragmentListModule(new DeliveryOwnFragmentListModule(view, onItemClickListener, context, fragment))
                .build();
    }

    public DeliveryOtherFragmentListComponent getDeliveryOtherFragmentListComponent(DeliveryOtherFragmentListView view, Fragment fragment, com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOtherFragmentListComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOtherFragmentListModule(new DeliveryOtherFragmentListModule(view, onItemClickListener, context, fragment))
                .build();

    }
    public DeliveryOtherFragmentComponent getDeliveryOtherFragmentComponent(DeliveryOtherFragmentView view, Fragment fragment, com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.OnItemClickListener onItemClickListener, Context context) {
        return DaggerDeliveryOtherFragmentComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(fragment))
                .deliveryOtherFragmentModule(new DeliveryOtherFragmentModule(view, onItemClickListener, context, fragment))
                .build();
    }
    public MaturitiesComponent getMaturitiesComponent(MaturitiesView view, Activity activity, Context context) {
        return DaggerMaturitiesComponent
                .builder()
                .managerCheckAppModule(managerCheckAppModule)
                .libsModule(new LibsModule(activity))
                .maturitiesModule(new MaturitiesModule(view,context,activity))
                .build();
    }
}
