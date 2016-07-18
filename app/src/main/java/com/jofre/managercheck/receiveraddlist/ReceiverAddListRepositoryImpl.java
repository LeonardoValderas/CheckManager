package com.jofre.managercheck.receiveraddlist;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveraddlist.events.CheckListEvent;
import com.raizlabs.android.dbflow.list.FlowCursorList;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverAddListRepositoryImpl implements ReceiverAddListRepository {
    EventBus eventBus;
    CheckController checkController;
    List<Check> checksList;
    public ReceiverAddListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void removeCheck(Check check) {
        if (check != null) {
            try {
              //  check.delete();
                post(CheckListEvent.deleteType, CheckListEvent.sucessDelete,check);
            } catch (Exception e) {
                post(CheckListEvent.deleteType, CheckListEvent.errorDelete + " " + e.getMessage(), false);
            }
        } else {
            post(CheckListEvent.deleteType, CheckListEvent.errorDelete, false);
        }
    }

    @Override
    public void selectAll(Context context) {
        checkController = new CheckController(context);
        checksList = checkController.selectAllCheck();
//        FlowCursorList<Check> storedChecks = new FlowCursorList<Check>(false, Check.class);
//        storedChecks.setCacheModels(true, Math.max(1, storedChecks.getCount()));
       if(checksList != null)
        post(CheckListEvent.selectType,checksList);
    }

    private void post(int type, String sucess,Check check) {
        post(type, null, sucess, null,check);
    }
    private void post(int type, List<Check> checkList) {
        post(type, checkList, null, null,null);
    }
    private void post(int type, String error, boolean data) {
        post(type, null, null, error,null);
    }

    private void post(int type, List<Check> checkList, String sucess, String error, Check check) {
        CheckListEvent event = new CheckListEvent();
        event.setError(error);
        event.setChecksList(checkList);
        event.setSucess(sucess);
        event.setType(type);
        event.setCheck(check);

        eventBus.post(event);
    }
}
