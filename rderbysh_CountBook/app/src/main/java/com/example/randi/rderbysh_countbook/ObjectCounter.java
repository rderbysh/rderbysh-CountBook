package com.example.randi.rderbysh_countbook;

import java.util.Date;

/**
 * Created by randi on 26/09/17.
 */

public class ObjectCounter {

    private Date date;
    private String object;
    private int initial_counter;
    private int current_counter;
    private String comment;

    private ObjectCounter() {

    }

    public ObjectCounter(String object, int initial_counter) {
        this.object = object;
        this.date = date;
        this.initial_counter = initial_counter;
    }

    public ObjectCounter(String object,int initial_counter, String comment) {
        this.object = object;
        this.date = getDate();
        this.initial_counter = initial_counter;
        this.current_counter = initial_counter;
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setInitial_counter(Integer initial_counter) {
        this.initial_counter = initial_counter;
    }

    public void setCurrent_counter(Integer current_counter) {
        this.current_counter = current_counter;
    }

    public Date getDate() {
        return date;
    }

    public String getObject() {
        return object;
    }

    public Integer getInitialCounter() {
        return initial_counter;
    }

    public Integer getCurrentCounter() {
        return current_counter;
    }

    @Override
    public String toString() {
        return "Object: " + object + " | Count: " + initial_counter + " | Comment: " + comment;
    }
}
