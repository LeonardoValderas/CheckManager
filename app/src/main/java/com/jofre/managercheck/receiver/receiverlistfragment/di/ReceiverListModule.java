package com.jofre.managercheck.receiver.receiverlistfragment.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListInteractor;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListInteractorImpl;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListPresenter;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListPresenterImpl;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListRepository;
import com.jofre.managercheck.receiver.receiverlistfragment.ReceiverListRepositoryImpl;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.ReceiverListView;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.adapters.ReceiverListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 8/7/2016.
 */
@Module
public class ReceiverListModule {
    ReceiverListView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;
    public ReceiverListModule(ReceiverListView view, OnItemClickListener onItemClickListener, Context context, Fragment Fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ReceiverListView providesReceiverAddListView() {
        return this.view;
    }

    @Provides
    @Singleton
    ReceiverListPresenter providesPhotoListPresenter(EventBus eventBus, ReceiverListView view, ReceiverListInteractor listInteractor) {
        return new ReceiverListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    ReceiverListInteractor providesReceiverAddListInteractor(ReceiverListRepository repository) {
        return new ReceiverListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ReceiverListRepository providesReceiverAddListRepository(EventBus eventBus, Context context) {
        return new ReceiverListRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    ReceiverListAdapter providesReceiverAddListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new ReceiverListAdapter(checkList, imageLoader, onItemClickListener, context,fragment);
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
