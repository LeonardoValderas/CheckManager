package com.argentinatecno.checkmanager.main.fragment_checks;

import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public class FragmentChecksInteractorImpl implements FragmentChecksInteractor {
    FragmentChecksRepository repository;

    public FragmentChecksInteractorImpl(FragmentChecksRepository repository) {
        this.repository = repository;
    }

    @Override
    public void removeCheck(List<Check> checks) {
        repository.removeCheck(checks);
    }

    @Override
    public void updateCheckDestiny(int id, String destiny, String destinyDate, boolean isUpdate) {
        repository.updateCheckDestiny(id, destiny, destinyDate, isUpdate);
    }

    @Override
    public void getChecks() {
        repository.selectAll();
    }

    @Override
    public void getChecksSearch(String s) {
        repository.getChecksSearch(s);
    }

}

