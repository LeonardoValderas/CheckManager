package com.jofre.managercheck.receivermain;

import com.jofre.managercheck.receivermain.events.ReceiverMainEvent;
import com.jofre.managercheck.receivermain.ui.ReceiverMainView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverMainPresenterImpl implements ReceiverMainPresenter {
    ReceiverMainView view;
    EventBus eventBus;
    ReceiverMainInteractor receiverMainInteractor;

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

        String error = event.getError();
        if (this.view != null) {
            switch (event.getType()) {
                case ReceiverMainEvent.ADD_INIT:
                    view.onAddInit();
                    break;
                case ReceiverMainEvent.ADD_COMPLETE:
                    view.onAddComplete();
                    break;
                case ReceiverMainEvent.ADD_ERROR:
                    view.onAddError(error);
                    break;
            }
        }
    }

//    @Override
//    public void saveCheck(Check check) {
//        if (this.view != null) {
//            view.saveAnimation();
//            view.hideUIElements();
//            view.showProgress();
//        }
//        saveRecipeInteractor.execute(recipe);
//
//    }
}
