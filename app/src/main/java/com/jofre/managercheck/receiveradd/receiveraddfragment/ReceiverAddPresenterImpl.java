package com.jofre.managercheck.receiveradd.receiveraddfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.receiveraddfragment.events.ReceiverAddEvent;
import com.jofre.managercheck.receiveradd.receiveraddfragment.ui.ReceiverAddView;


import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverAddPresenterImpl implements ReceiverAddPresenter {
    private EventBus eventBus;
    private ReceiverAddView view;
    private ReceiverAddInteractor interactor;

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
    public void isUpdate(Check check) {
        if (this.view != null)
            view.isUpdateIUElemente(check);
    }

    @Override
    public void saveCheck(Check check, Context context) {
        if (this.view != null) {
            view.unableUIComponent();
        }
        interactor.saveCheck(check, context);
    }

    @Override
    public void updateCheck(Check check, Context context) {
        if (this.view != null) {
            view.unableUIComponent();
        }
        interactor.updateCheck(check, context);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ReceiverAddEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error == null) {
                view.enableUIComponent();
                view.cleanUIComponent();
                view.onAddComplete();
            } else {
                view.enableUIComponent();
                view.onAddError(error);
            }
        }
    }

    @Override
    public ReceiverAddView getView() {
        return this.view;
    }
}
