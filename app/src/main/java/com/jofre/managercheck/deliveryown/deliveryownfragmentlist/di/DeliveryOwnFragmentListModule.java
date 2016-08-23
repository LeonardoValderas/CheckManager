package com.jofre.managercheck.deliveryown.deliveryownfragmentlist.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListInteractor;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListInteractorImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListPresenter;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListPresenterImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListRepository;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.DeliveryOwnFragmentListRepositoryImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.DeliveryOwnFragmentListView;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.adapters.OnItemClickListener;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.ui.adapters.DeliveryOwnFragmentListAdapter;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 8/7/2016.
 */
@Module
public class DeliveryOwnFragmentListModule {
    DeliveryOwnFragmentListView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;
    public DeliveryOwnFragmentListModule(DeliveryOwnFragmentListView view, OnItemClickListener onItemClickListener, Context context, Fragment Fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentListView providesDeliveryOwnFragmentListView() {
        return this.view;
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentListPresenter providesDeliveryOwnFragmentListPresenter(EventBus eventBus, DeliveryOwnFragmentListView view, DeliveryOwnFragmentListInteractor listInteractor) {
        return new DeliveryOwnFragmentListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentListInteractor providesDeliveryOwnFragmentListInteractor(DeliveryOwnFragmentListRepository repository) {
        return new DeliveryOwnFragmentListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentListRepository providesDeliveryOwnFragmentListRepository(EventBus eventBus, Context context) {
        return new DeliveryOwnFragmentListRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentListAdapter providesDeliveryOwnFragmentListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new DeliveryOwnFragmentListAdapter(checkList, imageLoader, onItemClickListener, context,fragment);
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
