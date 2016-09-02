package com.jofre.managercheck.maturities;

import android.content.Context;

import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.entities.CheckMaturities;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.maturities.events.MaturitiesEvent;
import com.jofre.managercheck.navigationmain.events.NavigationMainEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LEO on 16/8/2016.
 */
public class MaturitiesRepositoryImpl implements MaturitiesRepository {
    EventBus eventBus;
    CheckController checkController;
    int amountOwn = 0, amountOtherInBag = 0, amountOtherDelivery = 0, totalAmount = 0;
    int quantityOwn = 0, quantityOtherInBag = 0, quantityOtherDelivery = 0, totalQuantity = 0;
    String numbersOwn = null;
    String numbersOtherInBag = null;
    String numbersOtherDelivery = null;
    List<Check> arrayChecks = new ArrayList<Check>();
    CheckMaturities checkMaturities;
    Context context;

    public MaturitiesRepositoryImpl(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override
    public void selectMaturities(String since, String until) {
        try {
            if (since != null && until != null) {
                instanceController(context);

                arrayChecks = checkController.selectMaturities(since, until);

                if (arrayChecks != null) {
                    if (arrayChecks.size() > 0) {
                        checkMaturities = new CheckMaturities();
                        checkMaturities = new CheckMaturities();
                        for (int i = 0; i < arrayChecks.size(); i++) {

                            if (arrayChecks.get(i).getType() == 1) {//own
                                amountOwn += Integer.parseInt(arrayChecks.get(i).getAmount());
                                quantityOwn++;

                                if (quantityOwn == 1)
                                    numbersOwn = arrayChecks.get(i).getNumber();
                                else
                                    numbersOwn += " - " + arrayChecks.get(i).getNumber();


                            } else if (arrayChecks.get(i).getType() == 0 && (arrayChecks.get(i).getDestiny() == "" || arrayChecks.get(i).getDestiny() == null)) {//other in bag
                                amountOtherInBag += Integer.parseInt(arrayChecks.get(i).getAmount());
                                quantityOtherInBag++;

                                if (quantityOtherInBag == 1)
                                    numbersOtherInBag = arrayChecks.get(i).getNumber();
                                else
                                    numbersOtherInBag += " - " + arrayChecks.get(i).getNumber();
                            } else {
                                amountOtherDelivery += Integer.parseInt(arrayChecks.get(i).getAmount());
                                quantityOtherDelivery++;

                                if (quantityOtherDelivery == 1)
                                    numbersOtherDelivery = arrayChecks.get(i).getNumber();
                                else
                                    numbersOtherDelivery += "/" + arrayChecks.get(i).getNumber();
                            }
                        }

                        totalAmount = amountOtherDelivery + amountOtherInBag + amountOwn;
                        totalQuantity = quantityOtherDelivery + quantityOtherInBag + quantityOwn;

                        addEntity(checkMaturities);
                        post(checkMaturities);
                        cleanString();
                    }else{
                        post(MaturitiesEvent.emptySelect, false);
                    }
                }
            } else if (since == null) {
                post("Fecha invalida.(Hasta)", true);
            } else {
                post("Fecha invalida.(Desde)", true);
            }
        } catch (Exception e) {

            post(MaturitiesEvent.errorSelect, true);
        }
    }

    private void post(CheckMaturities checkMaturities, String error, String empty) {
        MaturitiesEvent event = new MaturitiesEvent();
        event.setError(error);
        event.setEmpty(empty);
        event.setCheckMaturities(checkMaturities);

        eventBus.post(event);
    }

    private void post(String errorOrEmpty, boolean isError) {
        if (isError)
            post(null, errorOrEmpty, null);
        else
            post(null, null, errorOrEmpty);
    }

    private void post(CheckMaturities checkMaturities) {
        post(checkMaturities, null, null);
    }

    public void instanceController(Context context) {
        checkController = new CheckController(context);
    }

    public void addEntity(CheckMaturities checkMaturities) {
        checkMaturities.setAmountTotal(String.valueOf(totalAmount));
        checkMaturities.setQuantityTotal(String.valueOf(totalQuantity));
        checkMaturities.setAmountOtherDelivery(String.valueOf(amountOtherDelivery));
        checkMaturities.setQuantityOtherDelivery(String.valueOf(quantityOtherDelivery));
        checkMaturities.setAmountOtherInBag(String.valueOf(amountOtherInBag));
        checkMaturities.setQuantityOtherInBag(String.valueOf(quantityOtherInBag));
        checkMaturities.setAmountOwn(String.valueOf(amountOwn));
        checkMaturities.setQuantityOwn(String.valueOf(quantityOwn));
        if (numbersOwn != null)
            checkMaturities.setNumbersOwn(numbersOwn);
        if (numbersOtherDelivery != null)
            checkMaturities.setNumbersOtherDelivery(numbersOtherDelivery);
        if (numbersOtherInBag != null)
            checkMaturities.setNumbersOtherInBag(numbersOtherInBag);

    }

    public void cleanString() {
        numbersOwn = null;
        numbersOtherInBag = null;
        numbersOtherDelivery = null;
        arrayChecks.isEmpty();
        amountOwn = 0;
        amountOtherInBag = 0;
        amountOtherDelivery = 0;
        totalAmount = 0;
        quantityOwn = 0;
        quantityOtherInBag = 0;
        quantityOtherDelivery = 0;
        totalQuantity = 0;
    }

}
