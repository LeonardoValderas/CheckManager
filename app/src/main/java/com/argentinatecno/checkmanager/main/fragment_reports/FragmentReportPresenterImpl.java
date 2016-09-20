package com.argentinatecno.checkmanager.main.fragment_reports;

import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_reports.events.FragmentReportEvent;
import com.argentinatecno.checkmanager.main.fragment_reports.ui.FragmentReportView;

import org.greenrobot.eventbus.Subscribe;

public class FragmentReportPresenterImpl implements FragmentReportPresenter {
    EventBus eventBus;
    FragmentReportView view;
    FragmentReportInteractor interactor;

    public FragmentReportPresenterImpl(EventBus eventBus, FragmentReportView view, FragmentReportInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getMaturitiesWeek(String date) {
        interactor.getMaturitiesWeek(date);
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
    public void onEventMainThread(FragmentReportEvent event) {
        if (this.view != null) {
            String error = event.getError();
            if (error == null) {
                view.setMaturitiesWeek(event.getMaturities());
            } else {
                view.getError(error);
            }
        }
    }
}
