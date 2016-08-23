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
    public void removeCheck(List<Check> checks) {
        interactor.removeCheck(checks);
    }

    @Override
    public void getChecks() {
        interactor.getChecks();
    }

    @Override
    @Subscribe
    public void onEventMainThread(DeliveryOtherFragmentEvent event) {
        if (this.view != null) {

            switch (event.getType()) {
                case DeliveryOtherFragmentEvent.selectType:
                    if (event.getChecksList() == null) {
                        view.errorShowList(ERROR_LIST);
                    } else if (event.getChecksList().isEmpty()) {
                        view.emptyList(EMPTY_LIST);
                    } else {
                        view.setChecks(event.getChecksList());
                    }
                    break;
                case DeliveryOtherFragmentEvent.deleteType:
                    String error = event.getError();
                    if (error == null) {
                        view.removeCheck(event.getCheck());
                        view.successDelete(event.getSucess());
                    } else {
                        view.errorDelete(error);
                    }
            }
        }
    }

    @Override
    public void showAlert(Context context, ImageLoader imageLoader, byte[] bytes) {
     //   new DeliveryOtherFragmentImageAdapter(context,imageLoader,bytes).alertDialog.show();
    }
}

