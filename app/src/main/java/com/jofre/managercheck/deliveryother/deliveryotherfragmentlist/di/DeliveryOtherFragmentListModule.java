package com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListInteractor;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListInteractorImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListPresenter;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListPresenterImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListRepository;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.DeliveryOtherFragmentListRepositoryImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.DeliveryOtherFragmentListView;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.DeliveryOtherFragmentListAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragmentlist.ui.adapters.OnItemClickListener;
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
public class DeliveryOtherFragmentListModule {
    DeliveryOtherFragmentListView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;
    public DeliveryOtherFragmentListModule(DeliveryOtherFragmentListView view, OnItemClickListener onItemClickListener, Context context, Fragment Fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentListView providesDeliveryOwnFragmentListView() {
        return this.view;
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentListPresenter providesDeliveryOwnFragmentListPresenter(EventBus eventBus, DeliveryOtherFragmentListView view, DeliveryOtherFragmentListInteractor listInteractor) {
        return new DeliveryOtherFragmentListPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentListInteractor providesDeliveryOwnFragmentListInteractor(DeliveryOtherFragmentListRepository repository) {
        return new DeliveryOtherFragmentListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentListRepository providesDeliveryOwnFragmentListRepository(EventBus eventBus, Context context) {
        return new DeliveryOtherFragmentListRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentListAdapter providesDeliveryOwnFragmentListAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new DeliveryOtherFragmentListAdapter(checkList, imageLoader, onItemClickListener, context,fragment);
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
