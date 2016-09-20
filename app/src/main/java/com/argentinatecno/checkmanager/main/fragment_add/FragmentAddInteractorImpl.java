package com.argentinatecno.checkmanager.main.fragment_add;

import android.content.Context;

import com.argentinatecno.checkmanager.entities.Check;

public class FragmentAddInteractorImpl implements FragmentAddInteractor {
    FragmentAddRepository repository;

    public FragmentAddInteractorImpl(FragmentAddRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveCheck(Check check, Context context) {
        repository.saveCheck(check, context);
    }

    @Override
    public void updateCheck(Check check, Context context) {
        repository.updateCheck(check, context);
    }
}
