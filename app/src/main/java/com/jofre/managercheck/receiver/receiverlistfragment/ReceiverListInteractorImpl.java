package com.jofre.managercheck.receiver.receiverlistfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class ReceiverListInteractorImpl implements ReceiverListInteractor {
    ReceiverListRepository repository;

    public ReceiverListInteractorImpl(ReceiverListRepository repository) {
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

