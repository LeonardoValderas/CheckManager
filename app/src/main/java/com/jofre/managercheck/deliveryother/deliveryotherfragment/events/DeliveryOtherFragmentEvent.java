package com.jofre.managercheck.deliveryother.deliveryotherfragment.events;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class DeliveryOtherFragmentEvent {
    private int type;
    private Check check;
    private List<Check> checksList;
    private String error;
    private String empty;
    private String sucess;

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public static final int selectType = 0;
    public static final int updateType = 1;
    public static final int backType = 2;

    public static final String sucessUpdate = "Cheque entregado correctamente.";
    public static final String sucessBack = "Cheque actualizado correctamente.";
    public static final String errorMsg = "Error al realizar la gestión.";
    public static final String errorEmpty = "Ingrese el destino del cheque.";
    public List<Check> getChecksList() {
        return checksList;
    }

    public void setChecksList(List<Check> checksList) {
        this.checksList = checksList;
    }

    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
