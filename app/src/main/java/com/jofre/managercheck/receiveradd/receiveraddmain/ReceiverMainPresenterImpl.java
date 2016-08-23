package com.jofre.managercheck.receiveradd.receiveraddmain;

import com.jofre.managercheck.receiveradd.receiveraddmain.events.ReceiverMainEvent;
import com.jofre.managercheck.receiveradd.receiveraddmain.ui.ReceiverMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverMainPresenterImpl implements ReceiverMainPresenter {
    private ReceiverMainView view;
    private EventBus eventBus;
    private ReceiverMainInteractor receiverMainInteractor;

    public ReceiverMainPresenterImpl(ReceiverMainView view, EventBus eventBus, ReceiverMainInteractor receiverMainInteractor) {
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
    public void onEventMainThread(ReceiverMainEvent event) {

        if (this.view != null) {
            switch (event.getType()) {
                case ReceiverMainEvent.ADD_INIT:
                    view.onAddInit();
                    break;
                case ReceiverMainEvent.ADD_COMPLETE:
                    view.onAddComplete();
                    break;
                case ReceiverMainEvent.ADD_ERROR:
                    view.onAddError(event.getError());
                    break;
            }
        }
    }

    @Override
    public ReceiverMainView getView() {
        return this.view;
    }

}
