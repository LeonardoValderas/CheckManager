package com.jofre.managercheck.receiver.receiverfragment;

import android.content.Context;

import com.jofre.managercheck.BaseTest;
import com.jofre.managercheck.BuildConfig;
import com.jofre.managercheck.entities.Check;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;

/**
 * Created by LEO on 13/8/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ReceiverAddInteractorImplTest extends BaseTest {
    @Mock
    private ReceiverFragmentRepository repository;
    @Mock
    Check check;
    @Mock
    Context context;

    private ReceiverFragmentInteractorImpl interactor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        interactor = new ReceiverFragmentInteractorImpl(repository);
    }
    @Test
    public void testSaveChecK_repositorySave(){
        interactor.saveCheck(check, context);
        verify(repository).saveCheck(check, context);
    }
    @Test
    public void testUpdateChecK_repositoryUpdate(){
        interactor.updateCheck(check, context);
        verify(repository).updateCheck(check, context);
    }

}
