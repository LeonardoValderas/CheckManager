package com.jofre.managercheck.navigationmain;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.CheckMaturities;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.navigationmain.events.NavigationMainEvent;

/**
 * Created by LEO on 16/8/2016.
 */
public class NavigationMainRepositoryImpl implements NavigationMainRepository {
    EventBus eventBus;
    CheckController checkController;
    CheckMaturities checkstAdd = new CheckMaturities();
    CheckMaturities checkstAddAux = new CheckMaturities();
    Context context;

    public NavigationMainRepositoryImpl(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }


    @Override
    public void getInformation() {
        instanceController(context);
//        checkstAddAux = checkController.QuantityAmountTotalCheckAdd();
//        if (checkstAddAux != null){
//            checkstAdd.setAmountTotal(checkstAddAux.getAmountTotal());
//            checkstAdd.setAmountQuantityTotal(checkstAddAux.getAmountQuantityTotal());
//            checkstAddAux = checkController.QuantityAmountWeekCheckAdd("","");
//        }

            post(NavigationMainEvent.ADD_COMPLETE, checkstAddAux, null);
    }

    private void post(int type, CheckMaturities checkAdd, String error) {
        NavigationMainEvent event = new NavigationMainEvent();
        event.setError(error);
        event.setCheckInformationAdd(checkAdd);
        event.setType(type);

        eventBus.post(event);
    }

    private void post(int type, String error) {
        NavigationMainEvent event = new NavigationMainEvent();
        event.setError(error);
        event.setType(type);

        eventBus.post(event);
    }

    public void instanceController(Context context) {
        checkController = new CheckController(context);
    }
}
