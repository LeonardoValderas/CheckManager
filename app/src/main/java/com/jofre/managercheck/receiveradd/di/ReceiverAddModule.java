package com.jofre.managercheck.receiveradd.di;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.ReceiverAddInteractor;
import com.jofre.managercheck.receiveradd.ReceiverAddInteractorImpl;
import com.jofre.managercheck.receiveradd.ReceiverAddPresenter;
import com.jofre.managercheck.receiveradd.ReceiverAddPresenterImpl;
import com.jofre.managercheck.receiveradd.ReceiverAddRepository;
import com.jofre.managercheck.receiveradd.ReceiverAddRepositoryImpl;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 5/7/2016.
 */
@Module
public class ReceiverAddModule {
    ReceiverAddView view;

    public ReceiverAddModule(ReceiverAddView view) {
        this.view = view;
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
}