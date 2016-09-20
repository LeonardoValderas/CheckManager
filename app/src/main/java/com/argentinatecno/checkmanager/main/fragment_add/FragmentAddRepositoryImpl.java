package com.argentinatecno.checkmanager.main.fragment_add;

import android.content.Context;

import com.argentinatecno.checkmanager.db.DataBaseController;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.fragment_add.events.FragmentAddEvent;

public class FragmentAddRepositoryImpl implements FragmentAddRepository {
    EventBus eventBus;
    DataBaseController dataBaseController;

    public FragmentAddRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void saveCheck(Check check, Context context) {
        if (check == null) {
            post("Error. Cierre la App y vuelva a intentarlo.");
        } else if (check.getNumber() == null || check.getNumber().isEmpty()) {
            post("Ingrese el numero de cheque.");
        } else if (check.getAmount() == null || check.getAmount().isEmpty()) {
            post("Ingrese el monto del cheque.");
        } else if (check.getExpiration() == null || check.getExpiration().equals("")) {
            post("Ingrese una fecha de vencimiento valida.");
        } else if ((check.getOrigin() == null || check.getOrigin().equals("")) && check.getType() == 0) {
            post("Ingrese el origen del cheque.");
        } else {
            try {
                instanceController(context);
                if (dataBaseController.insertCheck(check))
                    post();
                else
                    post("Error al guardar el cheque.");
            } catch (Exception e) {
                post(e.getMessage());
            }
        }
    }

    @Override
    public void updateCheck(Check check, Context context) {
        if (check == null) {
            post("Error. Cierre la App y vuelva a intentarlo.");
        } else if (check.getNumber() == null || check.getNumber().isEmpty()) {
            post("Ingrese el numero de cheque.");
        } else if (check.getAmount() == null || check.getAmount().isEmpty()) {
            post("Ingrese el monto del cheque.");
        } else if (check.getExpiration() == null || check.getExpiration().equals("")) {
            post("Ingrese una fecha de vencimiento valida.");
        } else if (check.getOrigin() == null || check.getOrigin().equals("")) {
            post("Ingrese el origen del cheque.");
        } else {
            instanceController(context);
            if (dataBaseController.updateCheck(check))
                post();
            else
                post("Error al actualizar el cheque.");
        }
    }

    private void post() {
        FragmentAddEvent addEvent = new FragmentAddEvent();
        addEvent.setType(FragmentAddEvent.COMPLETE_EVENT);
        eventBus.post(addEvent);
    }

    private void post(String errorMessage) {
        FragmentAddEvent addEvent = new FragmentAddEvent();
        addEvent.setError(errorMessage);
        eventBus.post(addEvent);
    }

    public void instanceController(Context context) {
        dataBaseController = new DataBaseController(context);
    }
}
