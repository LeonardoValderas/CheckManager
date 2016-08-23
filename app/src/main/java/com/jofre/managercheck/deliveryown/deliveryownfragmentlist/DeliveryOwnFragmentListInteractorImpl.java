package com.jofre.managercheck.deliveryown.deliveryownfragmentlist;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOwnFragmentListInteractorImpl implements DeliveryOwnFragmentListInteractor {
    DeliveryOwnFragmentListRepository repository;

    public DeliveryOwnFragmentListInteractorImpl(DeliveryOwnFragmentListRepository repository) {
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

