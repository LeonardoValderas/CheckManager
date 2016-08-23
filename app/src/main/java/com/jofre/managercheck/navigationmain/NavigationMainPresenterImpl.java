package com.jofre.managercheck.navigationmain;

import android.support.design.widget.NavigationView;

import com.jofre.managercheck.entities.CheckInformationAdd;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.navigationmain.events.NavigationMainEvent;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivityView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by LEO on 16/8/2016.
 */
public class NavigationMainPresenterImpl implements NavigationMainPresenter {
    EventBus eventBus;
    NavigationMainActivityView view;
    NavigationMainInteractor interactor;

    public NavigationMainPresenterImpl(EventBus eventBus, NavigationMainActivityView view, NavigationMainInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

//    @Override
//    public void setAddCheck(CheckInformationAdd checkInformationAdd) {
//
//    }
//
//    @Override
//    public void setDeliveryOwnCheck(String quantityTotal) {
//
//    }

    @Override
    public void getInformation() {
        interactor.getInformation();
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
    @Subscribe
    public void onEventMainThread(NavigationMainEvent event) {
        if (this.view != null) {

            switch (event.getType()) {

                case NavigationMainEvent.ADD_COMPLETE:
                    view.setAddCheck(event.getCheckInformationAdd());
                    break;
                case NavigationMainEvent.ADD_ERROR:
                    view.getError(event.getError());
            }
        }
    }
}
