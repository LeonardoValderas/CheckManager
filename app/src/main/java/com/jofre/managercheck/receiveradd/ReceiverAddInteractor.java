package com.jofre.managercheck.receiveradd;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;

/**
 * Created by LEO on 4/7/2016.
 */
public interface ReceiverAddInteractor {
void saveCheck(Check check);
}
