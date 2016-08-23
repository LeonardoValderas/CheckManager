package com.jofre.managercheck.deliveryother.deliveryotherfragmentlist;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOtherFragmentListInteractorImpl implements DeliveryOtherFragmentListInteractor {
    DeliveryOtherFragmentListRepository repository;

    public DeliveryOtherFragmentListInteractorImpl(DeliveryOtherFragmentListRepository repository) {
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

