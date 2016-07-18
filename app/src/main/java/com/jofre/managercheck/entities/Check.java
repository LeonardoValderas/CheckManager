package com.jofre.managercheck.entities;

import com.jofre.managercheck.db.ChecksDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.NotNull;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

public class Check {
    private int id_check;
    private String number;
    private String amount;
    private String expiration;
    private String origin;
    private byte[] photo;

    public Check(int id_check, String number, String amount, String expiration, String origin, byte[] photo) {
        this.id_check = id_check;
        this.number = number;
        this.amount = amount;
        this.expiration = expiration;
        this.origin = origin;
        this.photo = photo;
    }

    public Check() {
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public int getId_check() {
        return id_check;
    }
    public void setId_check(int id_check) {
        this.id_check = id_check;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getExpiration() {
        return expiration;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public byte[] getPhoto() {
        return photo;
    }
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
