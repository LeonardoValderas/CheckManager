package com.jofre.managercheck.receiveradd.receiveraddlistfragment.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListInteractor;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListInteractorImpl;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListPresenter;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListPresenterImpl;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListRepository;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ReceiverAddListRepositoryImpl;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.ReceiverAddListView;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.receiveradd.receiveraddlistfragment.ui.adapters.ReceiverAddListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 8/7/2016.
 */
@Module
public class ReceiverAddListModule {
    ReceiverAddListView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;
    public ReceiverAddListModule(ReceiverAddListView view, OnItemClickListener onItemClickListener, Context context, Fragment Fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ReceiverAddListView providesReceiverAddListView() {
        return this.view;
    }

    @Provides
    @Singleton
    ReceiverAddListPresenter providesPhotoListPresenter(EventBus eventBus, ReceiverAddListView view, ReceiverAddListInteractor listInteractor) {
        return new ReceiverAddListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    ReceiverAddListInteractor providesReceiverAddListInteractor(ReceiverAddListRepository repository) {
        return new ReceiverAddListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ReceiverAddListRepository providesReceiverAddListRepository(EventBus eventBus, Context context) {
        return new ReceiverAddListRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    ReceiverAddListAdapter providesReceiverAddListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new ReceiverAddListAdapter(checkList, imageLoader, onItemClickListener, context,fragment);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

//    @Provides
//    @Singleton
//    Context providesContext() {
//        return this.context;
//    }

    @Provides
    @Singleton
    List<Check> providesCheckList() {
        return new ArrayList<Check>();
    }

//    @Provides
//    @Singleton
//    DeliveryOtherFragmentImageAdapter providesReceiverAddListImageAdapter(Context context, ImageLoader imageLoader, Check check ) {
//        return new DeliveryOtherFragmentImageAdapter(context, imageLoader, check);
//    }
}
