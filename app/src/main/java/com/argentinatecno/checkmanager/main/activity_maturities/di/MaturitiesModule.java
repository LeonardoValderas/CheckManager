package com.argentinatecno.checkmanager.main.activity_maturities.di;

import android.app.Activity;
import android.content.Context;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesInteractor;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesInteractorImpl;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesPresenter;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesPresenterImpl;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesRepository;
import com.argentinatecno.checkmanager.main.activity_maturities.MaturitiesRepositoryImpl;
import com.argentinatecno.checkmanager.main.activity_maturities.adapters.ActivityMaturitiesAdapter;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesView;
import com.argentinatecno.checkmanager.main.activity_maturities.adapters.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MaturitiesModule {
    MaturitiesView view;
    Context context;
    Activity activity;
    OnItemClickListener onItemClickListener;


    public MaturitiesModule(MaturitiesView view, OnItemClickListener onItemClickListener, Context context, Activity activity) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.activity = activity;
    }

    @Provides
    @Singleton
    MaturitiesView providesMaturitiesView() {
        return this.view;
    }

    @Provides
    @Singleton
    MaturitiesPresenter providesMaturitiesPresenter(EventBus eventBus, MaturitiesView view, MaturitiesInteractor interactor) {
        return new MaturitiesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    MaturitiesInteractor providesMaturitiesInteractor(MaturitiesRepository repository) {
        return new MaturitiesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MaturitiesRepository providesMaturitiesRepository(Context context, EventBus eventBus) {
        return new MaturitiesRepositoryImpl(context, eventBus);
    }

    @Provides
    @Singleton
    ActivityMaturitiesAdapter providesActivityMaturitiesAdapter(List<Check> checkList, ImageLoader imageLoader, OnItemClickListener onItemClickListener, Context context, Activity activity) {
        return new ActivityMaturitiesAdapter(checkList, imageLoader, onItemClickListener, context, activity);
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
