package com.jofre.managercheck.navigationmain.di;

import android.app.Activity;
import android.content.Context;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.navigationmain.NavigationMainInteractor;
import com.jofre.managercheck.navigationmain.NavigationMainInteractorImpl;
import com.jofre.managercheck.navigationmain.NavigationMainPresenter;
import com.jofre.managercheck.navigationmain.NavigationMainPresenterImpl;
import com.jofre.managercheck.navigationmain.NavigationMainRepository;
import com.jofre.managercheck.navigationmain.NavigationMainRepositoryImpl;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivityView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 8/7/2016.
 */
@Module
public class NavigationMainModule {
    NavigationMainActivityView view;
    //   OnItemClickListener onItemClickListener;
    Context context;
    Activity activity;

    public NavigationMainModule(NavigationMainActivityView view, Context context, Activity activity) {
        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    @Provides
    @Singleton
    NavigationMainActivityView providesMainActivtyView() {
        return this.view;
    }

    @Provides
    @Singleton
    NavigationMainPresenter providesNavigationMainPresenter(EventBus eventBus, NavigationMainActivityView view, NavigationMainInteractor interactor) {
        return new NavigationMainPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    NavigationMainInteractor providesNavigationMainInteractor(NavigationMainRepository repository) {
        return new NavigationMainInteractorImpl(repository);
    }

    @Provides
    @Singleton
    NavigationMainRepository providesNavigationMainRepository(Context context, EventBus eventBus) {
        return new NavigationMainRepositoryImpl(context, eventBus);
    }

//    @Provides
//    @Singleton
//    ReceiverListAdapter providesReceiverAddListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
//        return new ReceiverListAdapter(checkList, imageLoader, onItemClickListener, context, fragment);
//    }
//
//    @Provides
//    @Singleton
//    OnItemClickListener providesOnItemClickListener() {
//        return this.onItemClickListener;
//    }
//
////    @Provides
////    @Singleton
////    Context providesContext() {
////        return this.context;
////    }
//
//    @Provides
//    @Singleton
//    List<Check> providesCheckList() {
//        return new ArrayList<Check>();
//    }

//    @Provides
//    @Singleton
//    DeliveryOtherFragmentImageAdapter providesReceiverAddListImageAdapter(Context context, ImageLoader imageLoader, Check check ) {
//        return new DeliveryOtherFragmentImageAdapter(context, imageLoader, check);
//    }
}
