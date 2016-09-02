package com.jofre.managercheck.receiver.receiverfragment;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiver.receiverfragment.events.ReceiverFragmentEvent;


/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverFragmentRepositoryImpl implements ReceiverFragmentRepository {
    EventBus eventBus;
    CheckController checkController;

    public ReceiverFragmentRepositoryImpl(EventBus eventBus) {
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
        } else if (check.getOrigin() == null || check.getOrigin().equals("")) {
            post("Ingrese el origen del cheque.");
        } else {
            try {
                instanceController(context);
                if (checkController.insertCheck(check))
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
            if (checkController.updateCheck(check))
                post();
            else
                post("Error al actualizar el cheque.");
        }
    }

    private void post() {
        ReceiverFragmentEvent addEvent = new ReceiverFragmentEvent();
        addEvent.setType(ReceiverFragmentEvent.COMPLETE_EVENT);
        eventBus.post(addEvent);
    }

    private void post(String errorMessage) {
        ReceiverFragmentEvent addEvent = new ReceiverFragmentEvent();
        addEvent.setError(errorMessage);
        eventBus.post(addEvent);
    }
    public void instanceController(Context context) {
        checkController = new CheckController(context);
    }
}
