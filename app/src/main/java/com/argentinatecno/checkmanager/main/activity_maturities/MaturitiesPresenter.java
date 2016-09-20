package com.argentinatecno.checkmanager.main.activity_maturities;

import com.argentinatecno.checkmanager.main.activity_maturities.events.MaturitiesEvent;

public interface MaturitiesPresenter {
    void onCreate();
    void onDestroy();
    void getMaturitiesChecks(String since, String until);
    void onEventMainThread(MaturitiesEvent event);

}
