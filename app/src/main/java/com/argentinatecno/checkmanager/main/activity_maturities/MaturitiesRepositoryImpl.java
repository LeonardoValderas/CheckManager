package com.argentinatecno.checkmanager.main.activity_maturities;

import android.content.Context;

import com.argentinatecno.checkmanager.db.DataBaseController;
import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.entities.Maturities;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.main.activity_maturities.events.MaturitiesEvent;

import java.util.ArrayList;
import java.util.List;


public class MaturitiesRepositoryImpl implements MaturitiesRepository {
    EventBus eventBus;
    DataBaseController dataBaseController;
    Double amountOwn = 0.0, amountOtherInBag = 0.0, amountOtherDelivery = 0.0, totalAmount = 0.0;
    int quantityOwn = 0, quantityOtherInBag = 0, quantityOtherDelivery = 0, totalQuantity = 0;
    String numbersOwn = null;
    String numbersOtherInBag = null;
    String numbersOtherDelivery = null;
    List<Check> arrayChecks = new ArrayList<Check>();
    Maturities maturities;
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

                arrayChecks = dataBaseController.selectMaturities(since, until);

                if (arrayChecks != null) {
                    maturities = new Maturities();
                    if (arrayChecks.size() > 0) {

                        for (int i = 0; i < arrayChecks.size(); i++) {

                            if (arrayChecks.get(i).getType() == 1) {//own
                                amountOwn += Double.parseDouble(arrayChecks.get(i).getAmount());
                                quantityOwn++;
                            } else if (arrayChecks.get(i).getType() == 0 && (arrayChecks.get(i).getDestiny() == "" || arrayChecks.get(i).getDestiny() == null)) {//other in bag
                                amountOtherInBag += Double.parseDouble(arrayChecks.get(i).getAmount());
                                quantityOtherInBag++;
                            } else {
                                amountOtherDelivery += Double.parseDouble(arrayChecks.get(i).getAmount());
                                quantityOtherDelivery++;
                            }
                        }

                        totalAmount = amountOtherDelivery + amountOtherInBag + amountOwn;
                        totalQuantity = quantityOtherDelivery + quantityOtherInBag + quantityOwn;

                        addEntityPost(maturities, false, arrayChecks);
                        cleanString();
                    } else {
                        cleanString();
                        addEntityPost(maturities, true, arrayChecks);
                    }
                }
            } else if (since == null) {
                post("Fecha invalida.(Desde)", true);
            } else if (until == null) {
                post("Fecha invalida.(Hasta)", true);
            }
        } catch (Exception e) {
            post(MaturitiesEvent.errorSelect, true);
        }
    }

    public void addEntityPost(Maturities maturities, boolean isEmpty, List<Check> checkList) {
        addEntity(maturities);
        if (isEmpty)
            post(MaturitiesEvent.emptySelect, false);
        else
            post(maturities);
    }

    private void post(Maturities checkMaturities, String error, String empty, List<Check> checkList) {
        MaturitiesEvent event = new MaturitiesEvent();
        event.setError(error);
        event.setEmpty(empty);
        event.setCheckList(checkList);
        event.setMaturities(checkMaturities);

        eventBus.post(event);
    }

    private void post(String errorOrEmpty, boolean isError) {
        if (isError)
            post(null, errorOrEmpty, null, null);
        else
            post(maturities, null, errorOrEmpty, arrayChecks);
    }

    private void post(Maturities maturities) {
        post(maturities, null, null, arrayChecks);
    }

    public void instanceController(Context context) {
        dataBaseController = new DataBaseController(context);
    }

    public void addEntity(Maturities maturities) {
        maturities.setAmountTotal(String.valueOf(totalAmount));
        maturities.setQuantityTotal(String.valueOf(totalQuantity));
        maturities.setAmountOther(String.valueOf(amountOtherDelivery+amountOtherInBag));
        maturities.setQuantityOther(String.valueOf(quantityOtherDelivery+quantityOtherInBag));
        maturities.setAmountOwn(String.valueOf(amountOwn));
        maturities.setQuantityOwn(String.valueOf(quantityOwn));
    }

    public void cleanString() {
        numbersOwn = "";
        numbersOtherInBag = "";
        numbersOtherDelivery = "";
        arrayChecks.isEmpty();
        amountOwn = 0.0;
        amountOtherInBag = 0.0;
        amountOtherDelivery = 0.0;
        totalAmount = 0.0;
        quantityOwn = 0;
        quantityOtherInBag = 0;
        quantityOtherDelivery = 0;
        totalQuantity = 0;
    }

}
