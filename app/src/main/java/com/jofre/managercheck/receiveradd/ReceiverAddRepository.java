package com.jofre.managercheck.receiveradd;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.receiveradd.events.ReceiverAddEvent;

/**
 * Created by LEO on 4/7/2016.
 */
public interface ReceiverAddRepository {
 void saveCheck (Check ckeck, Context context);
 void updateCheck (Check ckeck, Context context);
}
