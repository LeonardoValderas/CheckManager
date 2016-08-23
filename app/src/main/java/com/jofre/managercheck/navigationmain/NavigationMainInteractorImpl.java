package com.jofre.managercheck.navigationmain;

import android.support.design.widget.NavigationView;

import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.navigationmain.ui.NavigationMainActivityView;

/**
 * Created by LEO on 16/8/2016.
 */
public class NavigationMainInteractorImpl implements NavigationMainInteractor {
    NavigationMainRepository repository;

    public NavigationMainInteractorImpl(NavigationMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getInformation() {
        repository.getInformation();
    }
}
