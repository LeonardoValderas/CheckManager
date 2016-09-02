package com.jofre.managercheck.maturities;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.maturities.events.MaturitiesEvent;
import com.jofre.managercheck.maturities.ui.MaturitiesView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 8/7/2016.
 */
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
        interactor.getMaturitiesCheck(since,until);
    }

    @Override
    @Subscribe
    public void onEventMainThread(MaturitiesEvent event) {
        if (this.view != null) {
            String error = event.getError();
            String empty = event.getEmpty();
            if (error == null && empty == null) {
                if (event.getCheckMaturities() != null )
                    view.setMaturitiesCheck(event.getCheckMaturities());
            }else if(error != null){
                view.errorMaturities(error);
            }else {
                view.emptyMaturities(empty);
            }
        }
    }
}

