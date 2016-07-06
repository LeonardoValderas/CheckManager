package com.jofre.managercheck.entities;

import com.jofre.managercheck.db.ChecksDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by LEO on 3/7/2016.
 */
@Table(database = ChecksDataBase.class)
public class Check extends BaseModel {
    @Column
    @PrimaryKey
    int id_check;

    @Column
    String number;

    @Column
    String amount;


    @Column
    Date expiration;

    @Column
    String origin;

    @Column
    Blob photo;


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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }
}
