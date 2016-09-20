package com.argentinatecno.checkmanager.main.fragment_add.di;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddInteractor;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddInteractorImpl;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddPresenter;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddPresenterImpl;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddRepository;
import com.argentinatecno.checkmanager.main.fragment_add.FragmentAddRepositoryImpl;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAddView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentAddModule {
    FragmentAddView view;
    Fragment fragment;

    public FragmentAddModule(FragmentAddView view, Fragment fragment) {
        this.view = view;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    FragmentAddView providesFragmentAddView() {
        return this.view;
    }

    @Provides
    @Singleton
    FragmentAddPresenter providesFragmentAddPresenter(EventBus eventBus, FragmentAddView view, FragmentAddInteractor interactor) {
        return new FragmentAddPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    FragmentAddInteractor providesFragmentAddInteractor(FragmentAddRepository repository) {
        return new FragmentAddInteractorImpl(repository);
    }

    @Provides
    @Singleton
    FragmentAddRepository providesFragmentAddRepository(EventBus eventBus) {
        return new FragmentAddRepositoryImpl(eventBus);
    }
}
