package com.jofre.managercheck.receiver.receiverfragment.di;

import android.support.v4.app.Fragment;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentInteractor;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentInteractorImpl;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentPresenter;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentPresenterImpl;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentRepository;
import com.jofre.managercheck.receiver.receiverfragment.ReceiverFragmentRepositoryImpl;
import com.jofre.managercheck.receiver.receiverfragment.ui.ReceiverView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class ReceiverModule {
    ReceiverView view;
    Fragment fragment;

    public ReceiverModule(ReceiverView view) {
        this.view = view;
      //  this.fragment= fragment;
    }

    @Provides
    @Singleton
    ReceiverView providesReceiverAddView() {
        return this.view;
    }

    @Provides
    @Singleton
    ReceiverFragmentPresenter providesReceiverAddPresenter(EventBus eventBus, ReceiverView loginView, ReceiverFragmentInteractor receiverAddInteractor) {
        return new ReceiverFragmentPresenterImpl(eventBus, loginView, receiverAddInteractor);
    }

    @Provides
    @Singleton
    ReceiverFragmentInteractor providesReceiverAddInteractor(ReceiverFragmentRepository repository) {
        return new ReceiverFragmentInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ReceiverFragmentRepository providesReceiverAddRepository(EventBus eventBus) {
        return new ReceiverFragmentRepositoryImpl(eventBus);
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