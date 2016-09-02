package com.jofre.managercheck.deliveryown.deliveryownfragmentlist;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.deliveryown.deliveryownfragmentlist.events.DeliveryOwnFragmentListEvent;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOwnFragmentListRepositoryImpl implements DeliveryOwnFragmentListRepository {
    EventBus eventBus;
    CheckController checkController;
    List<Check> checksList;
    Context context;
    public DeliveryOwnFragmentListRepositoryImpl(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void removeCheck(List<Check> checks) {
        if (checks != null) {
            try {
                instanceController(context);
                checkController.deleteCheck(checks);
                post(DeliveryOwnFragmentListEvent.deleteType, DeliveryOwnFragmentListEvent.sucessDelete);
            } catch (Exception e) {
                post(DeliveryOwnFragmentListEvent.deleteType, DeliveryOwnFragmentListEvent.errorDelete + " " + e.getMessage(), false);
            }
        } else {
            post(DeliveryOwnFragmentListEvent.deleteType, DeliveryOwnFragmentListEvent.errorDelete, false);
        }
    }

    @Override
    public void selectAll() {
     instanceController(context);
     checksList = checkController.selectAllCheck(true);
//        FlowCursorList<Check> storedChecks = new FlowCursorList<Check>(false, Check.class);
//        storedChecks.setCacheModels(true, Math.max(1, storedChecks.getCount()));
       if(checksList != null)
        post(DeliveryOwnFragmentListEvent.selectType,checksList);
    }

    private void post(int type, String sucess,Check check) {
        post(type, null, sucess, null,check);
    }
    private void post(int type, String sucess) {
        post(type, null, sucess, null,null);
    }
    private void post(int type, List<Check> checkList) {
        post(type, checkList, null, null,null);
    }
    private void post(int type, String error, boolean data) {
        post(type, null, null, error,null);
    }

    private void post(int type, List<Check> checkList, String sucess, String error, Check check) {
        DeliveryOwnFragmentListEvent event = new DeliveryOwnFragmentListEvent();
        event.setError(error);
        event.setChecksList(checkList);
        event.setSucess(sucess);
        event.setType(type);
        event.setCheck(check);

        eventBus.post(event);
    }
    public void instanceController(Context context){
        checkController = new CheckController(context);
    }
}

