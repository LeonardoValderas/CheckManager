package com.argentinatecno.checkmanager.main.fragment_checks.di;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksInteractor;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksInteractorImpl;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksPresenter;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksPresenterImpl;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksRepository;
import com.argentinatecno.checkmanager.main.fragment_checks.FragmentChecksRepositoryImpl;
import com.argentinatecno.checkmanager.main.fragment_checks.adapters.FragmentChecksAdapter;
import com.argentinatecno.checkmanager.main.fragment_checks.ui.FragmentChecksView;
import com.argentinatecno.checkmanager.main.fragment_checks.adapters.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentChecksModule {
    FragmentChecksView view;
    OnItemClickListener onItemClickListener;
    Context context;
    Fragment fragment;

    public FragmentChecksModule(FragmentChecksView view, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    FragmentChecksView providesFragmentChecksView() {
        return this.view;
    }

    @Provides
    @Singleton
    FragmentChecksPresenter providesFragmentChecksPresenter(EventBus eventBus, FragmentChecksView view, FragmentChecksInteractor listInteractor) {
        return new FragmentChecksPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides
    @Singleton
    FragmentChecksInteractor providesFragmentChecksInteractor(FragmentChecksRepository repository) {
        return new FragmentChecksInteractorImpl(repository);
    }

    @Provides
    @Singleton
    FragmentChecksRepository providesFragmentChecksRepository(EventBus eventBus, Context context) {
        return new FragmentChecksRepositoryImpl(eventBus, context);
    }

    @Provides
    @Singleton
    FragmentChecksAdapter providesFragmentChecksAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Fragment fragment) {
        return new FragmentChecksAdapter(checkList, imageLoader, onItemClickListener, context, fragment);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Check> providesCheckList() {
        return new ArrayList<Check>();
    }

}
