package com.jofre.managercheck.receiver.receiverfragment;

import android.content.Context;

import com.jofre.managercheck.BaseTest;
import com.jofre.managercheck.BuildConfig;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiver.receiverfragment.events.ReceiverFragmentEvent;
import com.jofre.managercheck.receiver.receiverfragment.ui.ReceiverView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by LEO on 13/8/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ReceiverAddPresenterImplTest extends BaseTest {
    @Mock
    private ReceiverView view;
    @Mock
    private EventBus eventBus;
    @Mock
    private ReceiverFragmentInteractor receiverAddInteractor;
    @Mock
    private ReceiverFragmentEvent event;
    @Mock
    Check check;
    @Mock
    private Context context;

    private ReceiverFragmentPresenterImpl presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        presenter = new ReceiverFragmentPresenterImpl(eventBus, view, receiverAddInteractor);
    }

    @Test
    public void testOnCreate_subscribedToEventBus() throws Exception {
        presenter.onCreate();
        verify(eventBus).register(presenter);
    }

    @Test
    public void testOnDestroy_unsubscribedToEventBus() throws Exception {
        presenter.onDestroy();
        assertNull(presenter.getView());
        verify(eventBus).unregister(presenter);
    }

    @Test
    public void testIsUpdate_updateElements() throws Exception {
        presenter.isUpdate(check);
        assertNotNull(presenter.getView());
        verify(view).isUpdateIUElemente(check);
    }


    @Test
    public void testSaveCheck_interactorSave() throws Exception {
        presenter.saveCheck(check, context);
        assertNotNull(presenter.getView());
        verify(view).unableUIComponent();
        verify(receiverAddInteractor).saveCheck(check, context);
    }

    @Test
    public void testUpdateCheck_interactorUpdate() throws Exception {
        presenter.updateCheck(check, context);
        assertNotNull(presenter.getView());
        verify(view).unableUIComponent();
        verify(receiverAddInteractor).updateCheck(check, context);
    }

    @Test
    public void testOnEvent_hasError() throws Exception {

        String errorMsg = "Error";
        when(event.getError()).thenReturn(errorMsg);
        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        assertNotNull(event.getError());
        verify(view).enableUIComponent();
        verify(view).onAddError(errorMsg);

    }

    @Test
    public void testOnComplete_viewComplete() throws Exception {

        when(event.getError()).thenReturn(null);
        presenter.onEventMainThread(event);

        assertNotNull(presenter.getView());
        assertNull(event.getError());
        verify(view).enableUIComponent();
        verify(view).cleanUIComponent();
        verify(view).onAddComplete();
    }

}
