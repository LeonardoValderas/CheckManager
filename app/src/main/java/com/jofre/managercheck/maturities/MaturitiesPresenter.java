package com.jofre.managercheck.maturities;

import com.jofre.managercheck.maturities.events.MaturitiesEvent;

/**
 * Created by LEO on 8/7/2016.
 */
public interface MaturitiesPresenter {
    void onCreate();
    void onDestroy();
    void getMaturitiesChecks(String since, String until);
    void onEventMainThread(MaturitiesEvent event);

}
