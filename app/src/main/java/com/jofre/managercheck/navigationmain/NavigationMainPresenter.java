package com.jofre.managercheck.navigationmain;

import com.jofre.managercheck.entities.CheckInformationAdd;
import com.jofre.managercheck.navigationmain.events.NavigationMainEvent;

/**
 * Created by LEO on 16/8/2016.
 */
public interface NavigationMainPresenter {
//    void setAddCheck(CheckInformationAdd checkInformationAdd);
//    void setDeliveryOwnCheck(String quantityTotal);
    void getInformation();
    void onCreate();
    void onDestroy();
 //   void getAmountQuantityTotalAddCheck();
    void onEventMainThread(NavigationMainEvent event);
}
