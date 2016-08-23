package com.jofre.managercheck.receiveradd.receiveraddlistfragment.events;

import com.jofre.managercheck.entities.Check;

import java.util.List;

/**
 * Created by LEO on 8/7/2016.
 */
public class CheckListEvent {
    private int type;
    private Check check;
    private List<Check> checksList;
    private String error;
    private String sucess;
    public static final int deleteType = 0;
    public static final int selectType = 1;
    public static final int updateType = 2;

    public static final String sucessDelete = "Cheque eliminado.";
    public static final String errorDelete = "Error al intentar eliminar el cheque.";


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
