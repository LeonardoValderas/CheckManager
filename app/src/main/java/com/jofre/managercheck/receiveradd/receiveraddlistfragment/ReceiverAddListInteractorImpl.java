package com.jofre.managercheck.receiveradd.receiveraddlistfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverAddListInteractorImpl implements ReceiverAddListInteractor {
    ReceiverAddListRepository repository;

    public ReceiverAddListInteractorImpl(ReceiverAddListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void removeCheck(List<Check> checks) {
        repository.removeCheck(checks);
    }

    @Override
    public void getChecks() {
        repository.selectAll();
    }

}

