package com.argentinatecno.checkmanager.main.activity_maturities;

import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.activity_maturities.events.MaturitiesEvent;
import com.argentinatecno.checkmanager.main.activity_maturities.ui.MaturitiesView;

import org.greenrobot.eventbus.Subscribe;

public class MaturitiesPresenterImpl implements MaturitiesPresenter {
    EventBus eventBus;
    MaturitiesView view;
    MaturitiesInteractor interactor;

    public MaturitiesPresenterImpl(EventBus eventBus, MaturitiesView view, MaturitiesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void getMaturitiesChecks(String since, String until) {
        interactor.getMaturitiesCheck(since, until);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MaturitiesEvent event) {
        if (this.view != null) {
            String error = event.getError();
            String empty = event.getEmpty();
            if (error == null && empty == null) {
                if (event.getMaturities() != null)
                    view.setMaturitiesCheck(event.getMaturities());
                view.setMaturitiesAdapter(event.getCheckList());
            } else if (error != null) {
                view.errorMaturities(error);
            } else {
                view.emptyMaturities(empty);
                view.setMaturitiesCheck(event.getMaturities());
            }
        }
    }
}

