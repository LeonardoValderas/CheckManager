package com.jofre.managercheck.receiveradd.receiveraddfragment.di;

import android.support.v4.app.Fragment;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddInteractor;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddInteractorImpl;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddPresenter;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddPresenterImpl;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddRepository;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ReceiverAddRepositoryImpl;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ui.ReceiverAddView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class ReceiverAddModule {
    ReceiverAddView view;
    Fragment fragment;

    public ReceiverAddModule(ReceiverAddView view) {
        this.view = view;
      //  this.fragment= fragment;
    }

    @Provides
    @Singleton
    ReceiverAddView providesReceiverAddView() {
        return this.view;
    }

    @Provides
    @Singleton
    ReceiverAddPresenter providesReceiverAddPresenter(EventBus eventBus, ReceiverAddView loginView, ReceiverAddInteractor receiverAddInteractor) {
        return new ReceiverAddPresenterImpl(eventBus, loginView, receiverAddInteractor);
    }

    @Provides
    @Singleton
    ReceiverAddInteractor providesReceiverAddInteractor(ReceiverAddRepository repository) {
        return new ReceiverAddInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ReceiverAddRepository providesReceiverAddRepository(EventBus eventBus) {
        return new ReceiverAddRepositoryImpl(eventBus);
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