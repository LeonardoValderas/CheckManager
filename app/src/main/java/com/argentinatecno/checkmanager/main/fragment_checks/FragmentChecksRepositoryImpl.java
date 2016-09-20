package com.argentinatecno.checkmanager.main.fragment_checks;

import android.content.Context;

import com.argentinatecno.checkmanager.db.DataBaseController;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_checks.events.FragmentChecksEvent;

import java.util.List;

public class FragmentChecksRepositoryImpl implements FragmentChecksRepository {
    EventBus eventBus;
    DataBaseController dataBaseController;
    List<Check> checksList;
    Context context;

    public FragmentChecksRepositoryImpl(EventBus eventBus, Context context) {
        this.eventBus = eventBus;
        this.context = context;
    }

    @Override
    public void removeCheck(List<Check> checks) {
        if (checks != null) {
            try {
                instanceController(context);
                dataBaseController.deleteCheck(checks);
                post(FragmentChecksEvent.deleteType, FragmentChecksEvent.sucessDelete);
            } catch (Exception e) {
                post(FragmentChecksEvent.deleteType, FragmentChecksEvent.errorDelete + " " + e.getMessage(), false);
            }
        } else {
            post(FragmentChecksEvent.deleteType, FragmentChecksEvent.errorDelete, false);
        }
    }

    @Override
    public void updateCheckDestiny(int id, String destiny, String destinyDate, boolean isUpdate) {
        instanceController(context);
        if (destiny != null)
            if (!destiny.isEmpty()) {
                if (dataBaseController.updateCheckDestiny(id, destiny, destinyDate)) {
                    checksList = dataBaseController.selectAllCheck(false);
                    post(FragmentChecksEvent.updateType, checksList, FragmentChecksEvent.sucessUpdate);
                } else
                    post(FragmentChecksEvent.updateType, FragmentChecksEvent.errorUpdate, true);
            } else if (destiny.isEmpty() && !isUpdate) {
                if (dataBaseController.updateCheckDestiny(id, destiny, destinyDate)) {
                    checksList = dataBaseController.selectAllCheck(false);
                    post(FragmentChecksEvent.updateBackType, checksList, FragmentChecksEvent.sucessBackUpdate);
                } else
                    post(FragmentChecksEvent.updateBackType, FragmentChecksEvent.errorBackUpdate, true);
            } else {
                post(FragmentChecksEvent.updateBackType, false, FragmentChecksEvent.errorEmpty);
            }
    }

    @Override
    public void selectAll() {
        instanceController(context);
        checksList = dataBaseController.selectAllCheck(false);
//        FlowCursorList<Check> storedChecks = new FlowCursorList<Check>(false, Check.class);
//        storedChecks.setCacheModels(true, Math.max(1, storedChecks.getCount()));
        if (checksList != null)
            post(FragmentChecksEvent.selectType, checksList);
    }

    private void post(int type, String sucess, Check check) {
        post(type, null, sucess, null, check, null);
    }

    private void post(int type, String sucess) {
        post(type, null, sucess, null, null, null);
    }

    private void post(int type, List<Check> checkList) {
        post(type, checkList, null, null, null, null);
    }

    private void post(int type, List<Check> checkList, String sucess) {
        post(type, checkList, sucess, null, null, null);
    }

    private void post(int type, String error, boolean data) {
        post(type, null, null, error, null, null);
    }

    private void post(int type, boolean data, String empty) {
        post(type, null, null, null, null, empty);
    }


    private void post(int type, List<Check> checkList, String sucess, String error, Check check, String empty) {
        FragmentChecksEvent event = new FragmentChecksEvent();
        event.setError(error);
        event.setChecksList(checkList);
        event.setSucess(sucess);
        event.setType(type);
        event.setEmpty(empty);
        event.setCheck(check);

        eventBus.post(event);
    }

    public void instanceController(Context context) {
        dataBaseController = new DataBaseController(context);
    }
}

