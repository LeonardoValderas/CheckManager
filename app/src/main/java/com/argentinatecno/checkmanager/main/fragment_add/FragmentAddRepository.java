package com.argentinatecno.checkmanager.main.fragment_add;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;

public interface FragmentAddRepository {
    void saveCheck(Check ckeck, Context context);
    void updateCheck(Check ckeck, Context context);
}
