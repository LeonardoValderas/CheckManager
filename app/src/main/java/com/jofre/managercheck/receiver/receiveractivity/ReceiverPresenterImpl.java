package com.jofre.managercheck.receiver.receiveractivity;

import com.jofre.managercheck.receiver.receiveractivity.events.ReceiverActivityEvent;
import com.jofre.managercheck.receiver.receiveractivity.ui.ReceiverView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverPresenterImpl implements ReceiverPresenter {
    private ReceiverView view;
    private EventBus eventBus;
    private ReceiverInteractor receiverMainInteractor;

    public ReceiverPresenterImpl(ReceiverView view, EventBus eventBus, ReceiverInteractor receiverMainInteractor) {
        this.view = view;
        this.eventBus = eventBus;
        this.receiverMainInteractor = receiverMainInteractor;
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
    public void onEventMainThread(ReceiverActivityEvent event) {

        if (this.view != null) {
            switch (event.getType()) {
                case ReceiverActivityEvent.ADD_INIT:
                    view.onAddInit();
                    break;
                case ReceiverActivityEvent.ADD_COMPLETE:
                    view.onAddComplete();
                    break;
                case ReceiverActivityEvent.ADD_ERROR:
                    view.onAddError(event.getError());
                    break;
            }
        }
    }

    @Override
    public ReceiverView getView() {
        return this.view;
    }

}
