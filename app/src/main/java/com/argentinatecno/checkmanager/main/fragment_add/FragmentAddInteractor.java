package com.argentinatecno.checkmanager.main.fragment_add;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;

public interface FragmentAddInteractor {
    void saveCheck(Check check, Context context);

    void updateCheck(Check check, Context context);
}
