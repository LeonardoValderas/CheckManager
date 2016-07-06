package com.jofre.managercheck.receiveradd;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;


/**
 * Created by LEO on 4/7/2016.
 */
public class ReceiverAddRepositoryImpl implements ReceiverAddRepository {
    EventBus eventBus;

    public ReceiverAddRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void saveCheck(Check check) {
        if (check == null) {
            post("es null");
        } else if (check.getNumber() == null || check.getNumber().isEmpty()) {
            post("Ingrese el numero de cheque");
        } else if (check.getAmount() == null || check.getAmount().isEmpty()) {
            post("Ingrese el monto del cheque");
        } else if (check.getExpiration() == null || check.getExpiration().equals("")) {
            post("Ingrese la fecha de vencimiento del cheque");
        } else {
            check.save();
            post();
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
