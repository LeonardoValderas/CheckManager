package com.jofre.managercheck.maturities.di;

import android.app.Activity;
import android.content.Context;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.maturities.MaturitiesInteractor;
import com.jofre.managercheck.maturities.MaturitiesInteractorImpl;
import com.jofre.managercheck.maturities.MaturitiesPresenter;
import com.jofre.managercheck.maturities.MaturitiesPresenterImpl;
import com.jofre.managercheck.maturities.MaturitiesRepository;
import com.jofre.managercheck.maturities.MaturitiesRepositoryImpl;
import com.jofre.managercheck.maturities.ui.MaturitiesView;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by LEO on 8/7/2016.
 */
@Module
public class MaturitiesModule {
    MaturitiesView view;
    Context context;
    Activity activity;

    public MaturitiesModule(MaturitiesView view, Context context, Activity activity) {
        this.view = view;
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

}
