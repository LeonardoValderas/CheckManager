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
    public void updateCheck(int id, String delivery, String deliveryDate, boolean isUpdate) {
        repository.updateDelivery(id, delivery, deliveryDate, isUpdate);
    }

    @Override
    public void getChecks() {
        repository.selectAll();
    }

}

