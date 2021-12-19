package com.example.ma_reu.data.model;

import java.io.Serializable;

public class Participant implements Serializable {

    private long id;
    private String mail;
    private String name;

    /**
     * Constructor
     * @param name : String
     * @param mail : String
     * @param id : int
     */

    public Participant(long id, String mail,String name) {
        this.id = id;
        this.mail = mail;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
