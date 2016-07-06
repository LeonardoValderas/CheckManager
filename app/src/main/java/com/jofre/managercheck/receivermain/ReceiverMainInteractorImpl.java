package com.jofre.managercheck.receivermain;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverMainInteractorImpl implements ReceiverMainInteractor {
    private ReceiverMainRepository repository;

    public ReceiverMainInteractorImpl(ReceiverMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String path) {
        repository.addCheck(path);
    }
}
