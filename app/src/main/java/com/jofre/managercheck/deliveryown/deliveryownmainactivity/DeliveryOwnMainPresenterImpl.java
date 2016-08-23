package com.jofre.managercheck.deliveryown.deliveryownmainactivity;

import com.jofre.managercheck.deliveryown.deliveryownmainactivity.events.DeliveryOwnMainEvent;
import com.jofre.managercheck.deliveryown.deliveryownmainactivity.ui.DeliveryOwnMainView;
import com.jofre.managercheck.receiveradd.receiveraddmain.events.ReceiverMainEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 3/7/2016.
 */
public class DeliveryOwnMainPresenterImpl implements DeliveryOwnMainPresenter {
    DeliveryOwnMainView view;
    EventBus eventBus;
    DeliveryOwnMainInteractor deliveryOwnMainInteractor;

    public DeliveryOwnMainPresenterImpl(DeliveryOwnMainView view, EventBus eventBus, DeliveryOwnMainInteractor deliveryOwnMainInteractor) {
        this.view = view;
        this.eventBus = eventBus;
        this.deliveryOwnMainInteractor = deliveryOwnMainInteractor;
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
    @Subscribe
    public void onEventMainThread(ReceiverMainEvent event) {

        String error = event.getError();
        if (this.view != null) {
            switch (event.getType()) {
                case DeliveryOwnMainEvent.ADD_INIT:
                    view.onAddInit();
                    break;
                case DeliveryOwnMainEvent.ADD_COMPLETE:
                    view.onAddComplete();
                    break;
                case DeliveryOwnMainEvent.ADD_ERROR:
                    view.onAddError(error);
                    break;
            }
        }
    }
}
