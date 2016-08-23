package com.jofre.managercheck.deliveryown.deliveryownfragment;

import android.content.Context;

import com.jofre.managercheck.deliveryown.deliveryownfragment.events.DeliveryOwnFragmentEvent;
import com.jofre.managercheck.deliveryown.deliveryownfragment.ui.DeliveryOwnFragmentView;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 4/7/2016.
 */
public class DeliveryOwnFragmentPresenterImpl implements DeliveryOwnFragmentPresenter {
    EventBus eventBus;
    DeliveryOwnFragmentView view;
    DeliveryOwnFragmentInteractor interactor;

    public DeliveryOwnFragmentPresenterImpl(EventBus eventBus, DeliveryOwnFragmentView view, DeliveryOwnFragmentInteractor interactor) {
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
    public void siUpdate(Check check) {
        if(this.view != null)
        view.isUpdateIUElemente(check);
    }

    @Override
    public void saveCheck(Check check, Context context) {
        if (this.view != null) {
            view.unableUIComponent();
        }
        interactor.saveCheck(check,context);
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
    public void onEventMainThread(DeliveryOwnFragmentEvent event) {
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
}
