package com.jofre.managercheck.receiveradd;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;

import java.io.IOException;
import java.io.InvalidObjectException;


/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverAddRepositoryImpl implements ReceiverAddRepository {
    EventBus eventBus;
    CheckController checkController;

    public ReceiverAddRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void saveCheck(Check check, Context context) {
        if (check == null) {
            post("es null");
        } else if (check.getNumber() == null || check.getNumber().isEmpty()) {
            post("Ingrese el numero de cheque");
        } else if (check.getAmount() == null || check.getAmount().isEmpty()) {
            post("Ingrese el monto del cheque");
        } else if (check.getExpiration() == null || check.getExpiration().equals("")) {
            post("Ingrese la fecha de vencimiento del cheque");
        } else {
            try {
                checkController = new CheckController(context);
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
            post("es null");
        } else if (check.getNumber() == null || check.getNumber().isEmpty()) {
            post("Ingrese el numero de cheque");
        } else if (check.getAmount() == null || check.getAmount().isEmpty()) {
            post("Ingrese el monto del cheque");
        } else if (check.getExpiration() == null || check.getExpiration().equals("")) {
            post("Ingrese la fecha de vencimiento del cheque");
        } else {
            checkController = new CheckController(context);
            if (checkController.updateCheck(check))
                post();
            else
                post("Error al actualizar el cheque.");
        }
    }

    private void post() {
        ReceiverAddEvent addEvent = new ReceiverAddEvent();
        addEvent.setType(ReceiverAddEvent.COMPLETE_EVENT);
        eventBus.post(addEvent);
    }

    private void post(String errorMessage) {
        ReceiverAddEvent addEvent = new ReceiverAddEvent();
        addEvent.setError(errorMessage);
        eventBus.post(addEvent);
    }
}
