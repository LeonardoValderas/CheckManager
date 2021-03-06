package com.argentinatecno.checkmanager.entities;

public class Check {
    private int id_check;
    private int type;
    private String number;
    private String amount;
    private String expiration;
    private String origin;
    private String destiny;
    private String destinyDate;
    private byte[] photo;


    public Check(int id_check, int type, String number, String amount, String expiration, String origin, String destiny, String destinyDate, byte[] photo) {
        this.id_check = id_check;
        this.type = type;
        this.number = number;
        this.amount = amount;
        this.expiration = expiration;
        this.origin = origin;
        this.destiny = destiny;
        this.destinyDate = destinyDate;
        this.photo = photo;
    }

    public Check() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
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

    public String getDestinyDate() {
        return destinyDate;
    }

    public void setDestinyDate(String destinyDate) {
        this.destinyDate = destinyDate;
    }
}
