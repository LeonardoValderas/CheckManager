package com.jofre.managercheck.receiver.receiveractivity;

/**
 * Created by LEO on 3/7/2016.
 */
public class ReceiverInteractorImpl implements ReceiverInteractor {
    private ReceiverRepository repository;

    public ReceiverInteractorImpl(ReceiverRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String path) {
        repository.addCheck(path);
    }
}
