package com.jofre.managercheck.deliveryother.deliveryotherfragment;

import android.content.Context;

import com.jofre.managercheck.deliveryother.deliveryotherfragment.events.DeliveryOtherFragmentEvent;
import com.jofre.managercheck.deliveryother.deliveryotherfragment.ui.DeliveryOtherFragmentView;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageLoader;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOtherFragmentPresenterImpl implements DeliveryOtherFragmentPresenter {
    EventBus eventBus;
    DeliveryOtherFragmentView view;
    DeliveryOtherFragmentInteractor interactor;
    private final static String EMPTY_LIST = "Listado vacío";
    private final static String ERROR_LIST = "Error en el listado";


    public DeliveryOtherFragmentPresenterImpl(EventBus eventBus, DeliveryOtherFragmentView view, DeliveryOtherFragmentInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void updateCheck(int id, String destiny, String destinyDate, boolean isUpdate) {
        interactor.updateCheck(id, destiny, destinyDate, isUpdate);
    }

    @Override
    public void getChecks() {
        interactor.getChecks();
    }

    @Override
    public void closeDialogCancel() {
        view.closeDialogCancel();
    }

    @Override
    @Subscribe
    public void onEventMainThread(DeliveryOtherFragmentEvent event) {
        if (this.view != null) {
            switch (event.getType()) {
                case DeliveryOtherFragmentEvent.selectType:
                    if (event.getChecksList() == null)
                        view.errorShowList(ERROR_LIST);
                    else
                        view.setChecks(event.getChecksList());
                    break;
                case DeliveryOtherFragmentEvent.updateType:
                    String error = event.getError();
                    String empty = event.getEmpty();
                    if (error == null && empty == null)
                        view.closeDialogSuccess(event.getSucess(),event.getChecksList());
                    else if (empty != null)
                        view.dialogEmpty(empty);
                    else
                        view.closeDialogError(error);
                    break;
                case DeliveryOtherFragmentEvent.backType:
                    String errorBack = event.getError();
                    if (errorBack == null)
                        if (event.getSucess() != null)
                            view.closeDialogAccept(event.getSucess(),event.getChecksList());
                        else
                            view.closeDialogCancel();
                    else
                        view.closeDialogBackError(errorBack);
                    break;
            }
        }
    }
}

