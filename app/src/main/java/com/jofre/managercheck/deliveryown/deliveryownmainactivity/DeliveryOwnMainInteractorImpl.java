package com.jofre.managercheck.deliveryown.deliveryownmainactivity;

/**
 * Created by LEO on 3/7/2016.
 */
public class DeliveryOwnMainInteractorImpl implements DeliveryOwnMainInteractor {
    private DeliveryOwnMainRepository repository;

    public DeliveryOwnMainInteractorImpl(DeliveryOwnMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String path) {
        repository.addCheck(path);
    }
}
