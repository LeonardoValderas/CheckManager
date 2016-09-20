package com.argentinatecno.checkmanager.main.fragment_checks.events;

import com.argentinatecno.checkmanager.entities.Check;

import java.util.List;

public class FragmentChecksEvent {
    private int type;
    private Check check;
    private List<Check> checksList;
    private String error;
    private String sucess;


    private String empty;
    public static final int deleteType = 0;
    public static final int selectType = 1;
    public static final int updateType = 2;
    public static final int updateBackType = 3;

    public static final String sucessDelete = "Cheque eliminado.";
    public static final String errorDelete = "Error al intentar eliminar el cheque.";
    public static final String sucessUpdate = "Cheque entregado.";
    public static final String errorUpdate = "Error al intentar realizar la entrega del cheque.";
    public static final String sucessBackUpdate = "Cheque actualizado.";
    public static final String errorBackUpdate = "Error al intentar actualizar al cheque.";
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

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }
}
