package com.argentinatecno.checkmanager.main.fragment_reports.di;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportInteractor;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportInteractorImpl;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportPresenter;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportPresenterImpl;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportRepository;
import com.argentinatecno.checkmanager.main.fragment_reports.FragmentReportRepositoryImpl;
import com.argentinatecno.checkmanager.main.fragment_reports.ui.FragmentReportView;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentReportModule {
    FragmentReportView view;
    //   OnItemClickListener onItemClickListener;
    Context context;
    Activity activity;
    Fragment fragment;

    public FragmentReportModule(FragmentReportView view, Context context, Fragment fragment) {
        this.view = view;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    FragmentReportView providesFragmentReportView() {
        return this.view;
    }

    @Provides
    @Singleton
    FragmentReportPresenter providesFragmentReportPresenter(EventBus eventBus, FragmentReportView view, FragmentReportInteractor interactor) {
        return new FragmentReportPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    FragmentReportInteractor providesFragmentReportInteractor(FragmentReportRepository repository) {
        return new FragmentReportInteractorImpl(repository);
    }

    @Provides
    @Singleton
    FragmentReportRepository providesNavigationMainRepository(Context context, EventBus eventBus) {
        return new FragmentReportRepositoryImpl(context, eventBus);
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
