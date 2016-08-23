package com.jofre.managercheck.deliveryother.deliveryotherfragment.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentInteractor;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentInteractorImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentPresenter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentPresenterImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentRepository;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.DeliveryOtherFragmentRepositoryImpl;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragmentView;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.DeliveryOtherFragmentAdapter;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.adapters.OnItemClickListener;
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
public class DeliveryOtherFragmentModule {
    DeliveryOtherFragmentView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;
    public DeliveryOtherFragmentModule(DeliveryOtherFragmentView view, OnItemClickListener onItemClickListener, Context context, Fragment Fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentView providesDeliveryOtherFragmentView() {
        return this.view;
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentPresenter providesDeliveryOtherFragmentPresenter(EventBus eventBus, DeliveryOtherFragmentView view, DeliveryOtherFragmentInteractor listInteractor) {
        return new DeliveryOtherFragmentPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentInteractor providesDeliveryOtherFragmentInteractor(DeliveryOtherFragmentRepository repository) {
        return new DeliveryOtherFragmentInteractorImpl(repository);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentRepository providesDeliveryOtherFragmentRepository(EventBus eventBus, Context context) {
        return new DeliveryOtherFragmentRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    DeliveryOtherFragmentAdapter providesDeliveryOtherFragmentAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new DeliveryOtherFragmentAdapter(checkList, imageLoader, onItemClickListener, context,fragment);
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
