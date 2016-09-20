package com.argentinatecno.checkmanager.main.fragment_add;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;
import com.argentinatecno.checkmanager.main.fragment_add.events.FragmentAddEvent;
import com.argentinatecno.checkmanager.main.fragment_add.ui.FragmentAddView;

public interface FragmentAddPresenter {
    void onCreate();

    void onDestroy();

    void isUpdate(Check check);

    void saveCheck(Check check, Context context);

    void updateCheck(Check check, Context context);

    void onEventMainThread(FragmentAddEvent event);

    FragmentAddView getView();
}
