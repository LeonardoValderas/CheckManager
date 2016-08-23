package com.jofre.managercheck.receiveradd.receiveraddfragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jofre.managercheck.BaseTest;
import com.jofre.managercheck.BuildConfig;
import com.jofre.managercheck.db.CheckController;
import com.jofre.managercheck.db.SQLiteDataBaseCheck;
import com.jofre.managercheck.entities.Check;
import com.jofre.managercheck.lib.base.EventBus;
import com.jofre.managercheck.receiveradd.receiveraddfragment.events.ReceiverAddEvent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by LEO on 13/8/2016.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ReceiverAddRepositoryImplTest extends BaseTest {
    @Mock
    private EventBus eventBus;
    @Mock
    private Check check;
    @Mock
    private Context context;
    @Mock
    private CheckController checkController;
    @Mock
    SQLiteDataBaseCheck sqLiteDataBaseCheck;
    @Mock
    SQLiteDatabase database;

    private ReceiverAddRepositoryImpl repository;
    private ArgumentCaptor<ReceiverAddEvent> receiverAddEventArgumentCaptor;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        repository = new ReceiverAddRepositoryImpl(eventBus);
        receiverAddEventArgumentCaptor = ArgumentCaptor.forClass(ReceiverAddEvent.class);
    }

    @Test
    public void testCheckNull_postNull() {
        check = null;
        // when(check).thenReturn(null);
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Error. Cierre la App y vuelva a intentarlo.", event.getError());
    }
    @Test
    public void testCheckNumberNull_postNull() {
        when(check.getNumber()).thenReturn(null);
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el numero de cheque.", event.getError());
    }
    @Test
    public void testCheckNumberEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("");
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el numero de cheque.", event.getError());
    }

    @Test
    public void testCheckAmountNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn(null);
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el monto del cheque.", event.getError());
    }

    @Test
    public void testCheckAmountEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("");
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el monto del cheque.", event.getError());
    }
    @Test
    public void testCheckExpitationNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn(null);
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese una fecha de vencimiento valida.", event.getError());
    }
    @Test
    public void testCheckExpitationEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("");
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese una fecha de vencimiento valida.", event.getError());
    }
    @Test
    public void testCheckOriginNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn(null);
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el origen del cheque.", event.getError());
    }
    @Test
    public void testCheckOriginEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("");
        repository.saveCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el origen del cheque.", event.getError());
    }
    @Test
    public void testCheckSaveTrue_InsertCheck() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("4");
        when(check.getPhoto()).thenReturn(null);
        checkController.insertCheck(check);
        when(checkController.insertCheck(check)).thenReturn(true);
        repository.saveCheck(check, context);
        verify(checkController).insertCheck(check);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals(ReceiverAddEvent.COMPLETE_EVENT, event.getType());
    }
    @Test
    public void testCheckSaveFalse_InsertCheck() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("4");
        when(check.getPhoto()).thenReturn(null);
        checkController.insertCheck(check);
        when(checkController.insertCheck(check)).thenReturn(false);
        repository.saveCheck(check, context);
        verify(checkController).insertCheck(check);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
     //   assertEquals("Error al guardar el cheque.", event.getError());
    }
    @Test
    public void testCheckUpdateNull_postNull() {
        check = null;
        // when(check).thenReturn(null);
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Error. Cierre la App y vuelva a intentarlo.", event.getError());
    }
    @Test
    public void testCheckUpdateNumberNull_postNumberNull() {
        when(check.getNumber()).thenReturn(null);
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el numero de cheque.", event.getError());
    }
    @Test
    public void testCheckUpdateNumberEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("");
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el numero de cheque.", event.getError());
    }

    @Test
    public void testCheckUpdateAmountNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn(null);
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el monto del cheque.", event.getError());
    }

    @Test
    public void testCheckUpdateAmountEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("");
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el monto del cheque.", event.getError());
    }
    @Test
    public void testCheckUpdateExpitationNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn(null);
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese una fecha de vencimiento valida.", event.getError());
    }
    @Test
    public void testCheckUpdateExpitationEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("");
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese una fecha de vencimiento valida.", event.getError());
    }
    @Test
    public void testCheckUpdateOriginNull_postNull() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn(null);
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el origen del cheque.", event.getError());
    }
    @Test
    public void testCheckUpdateOriginEmpty_postEmpty() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("");
        repository.updateCheck(check, context);
        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        assertEquals("Ingrese el origen del cheque.", event.getError());
    }
    @Test
    public void testCheckUpdateTrue_InsertCheck() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("4");
        when(check.getPhoto()).thenReturn(null);
        when(checkController.updateCheck(check)).thenReturn(true);
        checkController.updateCheck(check);
      //  repository.updateCheck(check, context);
    //    verify(checkController).updateCheck(check);
//        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
//        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
//        assertEquals(ReceiverAddEvent.COMPLETE_EVENT, event.getType());
    }
    @Test
    public void testCheckUpdateFalse_InsertCheck() {
        when(check.getNumber()).thenReturn("1");
        when(check.getAmount()).thenReturn("2");
        when(check.getExpiration()).thenReturn("3");
        when(check.getOrigin()).thenReturn("4");
        when(check.getPhoto()).thenReturn(null);
        when(checkController.updateCheck(check)).thenReturn(false);
     //   checkController.updateCheck(check);
//        repository.updateCheck(check, context);
//        verify(checkController).updateCheck(check);
//        verify(eventBus).post(receiverAddEventArgumentCaptor.capture());
//        ReceiverAddEvent event = receiverAddEventArgumentCaptor.getValue();
        //   assertEquals("Error al guardar el cheque.", event.getError());
    }
}
