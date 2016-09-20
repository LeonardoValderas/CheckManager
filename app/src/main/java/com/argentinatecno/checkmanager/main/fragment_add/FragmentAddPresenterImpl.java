package com.argentinatecno.checkmanager.main.fragment_add;


import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_add.events.FragmentAddEvent;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAddView;

import org.greenrobot.eventbus.Subscribe;


public class FragmentAddPresenterImpl implements FragmentAddPresenter {
    private FragmentAddView view;
    private EventBus eventBus;
    private FragmentAddInteractor interactor;

    public FragmentAddPresenterImpl(EventBus eventBus, FragmentAddView view, FragmentAddInteractor interactor) {
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
    public void onEventMainThread(FragmentAddEvent event) {
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
    public FragmentAddView getView() {
        return this.view;
    }
}
