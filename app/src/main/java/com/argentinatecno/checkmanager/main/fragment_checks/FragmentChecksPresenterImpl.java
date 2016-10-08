package com.argentinatecno.checkmanager.main.fragment_checks;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.EventBus;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.fragment_checks.events.FragmentChecksEvent;
import com.argentinatecno.checkmanager.main.fragment_checks.ui.FragmentChecksView;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FragmentChecksPresenterImpl implements FragmentChecksPresenter {
    EventBus eventBus;
    FragmentChecksView view;
    FragmentChecksInteractor interactor;
    private final static String EMPTY_LIST = "Listado vacío";
    private final static String ERROR_LIST = "Error en el listado";


    public FragmentChecksPresenterImpl(EventBus eventBus, FragmentChecksView view, FragmentChecksInteractor interactor) {
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
    public void closeDialogCancel() {
        view.closeDialogCancel();
    }

    @Override
    public void updateCheckDestiny(int id, String destiny, String destinyDate, boolean isUpdate) {
        interactor.updateCheckDestiny(id, destiny, destinyDate, isUpdate);
    }

    @Override
    @Subscribe
    public void onEventMainThread(FragmentChecksEvent event) {
        if (this.view != null) {

            switch (event.getType()) {
                case FragmentChecksEvent.selectType:
                    if (event.getChecksList() == null) {
                        view.errorShowList(ERROR_LIST);
                    } else if (event.getChecksList().isEmpty()) {
                        view.emptyList(EMPTY_LIST);
                    } else {
                        view.setChecks(event.getChecksList());
                    }
                    break;
                case FragmentChecksEvent.deleteType:
                    String error = event.getError();
                    if (error == null) {
                        view.removeCheck(event.getCheck());
                        view.successDelete(event.getSucess());
                    } else {
                        view.errorDelete(error);
                    }
                    break;
                case FragmentChecksEvent.updateType:
                    String errorUpdate = event.getError();
                    if (errorUpdate == null) {
                        view.closeDialogUpdateSuccess(event.getSucess());
                    } else {
                        view.closeDialogUpdateError(errorUpdate);
                    }
                    break;
                case FragmentChecksEvent.updateBackType:
                    String errorBackUpdate = event.getError();
                    if (errorBackUpdate == null) {
                        view.closeDialogUpdateBackSuccess(event.getSucess());
                    } else {
                        view.closeDialogUpdateBackError(errorBackUpdate);
                    }
                    break;
                case FragmentChecksEvent.selectSearchType:
                    String emptySearch = event.getEmpty();
                    if (emptySearch == null)
                        view.setChecksSearch(event.getChecksList());
                    else {
                        view.setChecksSearch(new ArrayList<Check>());
                        view.emptyList(emptySearch);
                    }
                    break;
            }
        }
    }

    @Override
    public void showAlert(Context context, ImageLoader imageLoader, byte[] bytes) {
        //   new DeliveryOtherFragmentImageAdapter(context,imageLoader,bytes).alertDialog.show();
    }

    @Override
    public void getChecksSearch(String s) {
        interactor.getChecksSearch(s);
    }
}

