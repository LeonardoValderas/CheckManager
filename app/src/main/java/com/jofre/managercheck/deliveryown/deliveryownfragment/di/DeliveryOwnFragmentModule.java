package com.jofre.managercheck.deliveryown.deliveryownfragment.di;

import android.support.v4.app.Fragment;

import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentInteractor;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentInteractorImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentPresenter;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentPresenterImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentRepository;
import com.jofre.managercheck.deliveryown.deliveryownfragment.DeliveryOwnFragmentRepositoryImpl;
import com.jofre.managercheck.deliveryown.deliveryownfragment.ui.DeliveryOwnFragmentView;
import com.jofre.managercheck.lib.base.EventBus;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class DeliveryOwnFragmentModule {
    DeliveryOwnFragmentView view;
    Fragment fragment;

    public DeliveryOwnFragmentModule (DeliveryOwnFragmentView view) {
        this.view = view;
      //  this.fragment= fragment;
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentView providesDeliveryOwnFragmentView() {
        return this.view;
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentPresenter providesDeliveryOwnFragmentPresenter(EventBus eventBus, DeliveryOwnFragmentView loginView, DeliveryOwnFragmentInteractor deliveryOwnFragmentInteractor) {
        return new DeliveryOwnFragmentPresenterImpl(eventBus, loginView, deliveryOwnFragmentInteractor);
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentInteractor providesDeliveryOwnFragmentInteractor(DeliveryOwnFragmentRepository repository) {
        return new DeliveryOwnFragmentInteractorImpl(repository);
    }

    @Provides
    @Singleton
    DeliveryOwnFragmentRepository providesDeliveryOwnFragment(EventBus eventBus) {
        return new DeliveryOwnFragmentRepositoryImpl(eventBus);
    }
//    @Provides
//    @Singleton
//    ImageLoader providesImageLoader(Fragment fragment) {
//        GlideImageLoader imageLoader = new GlideImageLoader();
//        if (fragment != null) {
//            imageLoader.setLoaderContext(fragment);
//        }
//        return imageLoader;
//    }
}