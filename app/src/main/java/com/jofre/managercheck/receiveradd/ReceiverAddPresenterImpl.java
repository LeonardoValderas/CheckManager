package com.jofre.managercheck.receiveradd;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;
import com.jofre.managercheck.receiveradd.ui.ReceiverAddView;
import com.jofre.managercheck.receivermain.events.ReceiverMainEvent;


import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverAddPresenterImpl implements ReceiverAddPresenter {
    EventBus eventBus;
    ReceiverAddView view;
    ReceiverAddInteractor interactor;

    public ReceiverAddPresenterImpl(EventBus eventBus, ReceiverAddView view, ReceiverAddInteractor interactor) {
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
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void saveCheck(Check check) {
        if (this.view != null) {
            view.hideUIComponent();
            view.showProgress();
        }
        interactor.saveCheck(check);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ReceiverAddEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error == null) {
                view.hideProgress();
                view.showUIComponent();
                view.onAddComplete();
            } else {
                view.hideProgress();
                view.showUIComponent();
                view.onAddError(error);
            }
        }
    }
}
