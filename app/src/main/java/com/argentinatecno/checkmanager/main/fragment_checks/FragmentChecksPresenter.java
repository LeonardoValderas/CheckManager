package com.argentinatecno.checkmanager.main.fragment_checks;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.lib.base.ImageLoader;
import com.argentinatecno.checkmanager.main.fragment_checks.events.FragmentChecksEvent;

import java.util.List;

public interface FragmentChecksPresenter {
    void onCreate();
    void onDestroy();
    void removeCheck(List<Check> checks);
    void getChecks();
    void closeDialogCancel();
    void updateCheckDestiny(int id, String destiny, String destinyDate, boolean isUpdate);
    void onEventMainThread(FragmentChecksEvent event);
    void showAlert(Context context, ImageLoader imageLoader, byte[] bytes);
}
