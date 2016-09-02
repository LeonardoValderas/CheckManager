package com.jofre.managercheck.receiver.receiverfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiver.receiverfragment.events.ReceiverFragmentEvent;
import com.jofre.managercheck.receiver.receiverfragment.ui.ReceiverView;


import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverFragmentPresenterImpl implements ReceiverFragmentPresenter {
    private EventBus eventBus;
    private ReceiverView view;
    private ReceiverFragmentInteractor interactor;

    public ReceiverFragmentPresenterImpl(EventBus eventBus, ReceiverView view, ReceiverFragmentInteractor interactor) {
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
    public void onEventMainThread(ReceiverFragmentEvent event) {
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
    public ReceiverView getView() {
        return this.view;
    }
}
