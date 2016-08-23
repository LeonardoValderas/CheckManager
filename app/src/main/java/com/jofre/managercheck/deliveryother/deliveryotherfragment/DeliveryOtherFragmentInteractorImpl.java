package com.jofre.managercheck.deliveryother.deliveryotherfragment;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOtherFragmentInteractorImpl implements DeliveryOtherFragmentInteractor {
    DeliveryOtherFragmentRepository repository;

    public DeliveryOtherFragmentInteractorImpl(DeliveryOtherFragmentRepository repository) {
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

