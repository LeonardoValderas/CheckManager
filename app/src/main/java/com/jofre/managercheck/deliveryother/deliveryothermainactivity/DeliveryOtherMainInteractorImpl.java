package com.jofre.managercheck.deliveryother.deliveryothermainactivity;

/**
 * Created by LEO on 3/7/2016.
 */
public class DeliveryOtherMainInteractorImpl implements DeliveryOtherMainInteractor {
    private DeliveryOtherMainRepository repository;

    public DeliveryOtherMainInteractorImpl(DeliveryOtherMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String path) {
        repository.addCheck(path);
    }
}
