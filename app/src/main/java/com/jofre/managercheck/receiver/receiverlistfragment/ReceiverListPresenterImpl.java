package com.jofre.managercheck.receiver.receiverlistfragment;

import android.content.Context;

import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.lib.base.ImageLoader;
import com.jofre.managercheck.receiver.receiverlistfragment.events.ReceiverListEvent;
import com.jofre.managercheck.receiver.receiverlistfragment.ui.ReceiverListView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverListPresenterImpl implements ReceiverListPresenter {
    EventBus eventBus;
    ReceiverListView view;
    ReceiverListInteractor interactor;
    private final static String EMPTY_LIST = "Listado vacío";
    private final static String ERROR_LIST = "Error en el listado";


    public ReceiverListPresenterImpl(EventBus eventBus, ReceiverListView view, ReceiverListInteractor interactor) {
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
    public void onEventMainThread(ReceiverListEvent event) {
        if (this.view != null) {

            switch (event.getType()) {
                case ReceiverListEvent.selectType:
                    if (event.getChecksList() == null) {
                        view.errorShowList(ERROR_LIST);
                    } else if (event.getChecksList().isEmpty()) {
                        view.emptyList(EMPTY_LIST);
                    } else {
                        view.setChecks(event.getChecksList());
                    }
                    break;
                case ReceiverListEvent.deleteType:
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

