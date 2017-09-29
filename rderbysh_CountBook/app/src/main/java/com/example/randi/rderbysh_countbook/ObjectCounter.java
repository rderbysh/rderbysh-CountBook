package com.example.randi.rderbysh_countbook;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by randi on 26/09/17.
 */

public class ObjectCounter {

    private Calendar date;
    private String object;
    private int initial_counter;
    private int current_counter;
    private String comment;

    protected ObjectCounter() {

    }

    public ObjectCounter(String object, int initial_counter) {
        this.object = object;
        this.initial_counter = initial_counter;
    }

    public ObjectCounter(String object,int initial_counter, String comment) {
        this.object = object;
        date = Calendar.getInstance();
        date.setTime(new Date());
        this.initial_counter = initial_counter;
        this.current_counter = initial_counter;
        this.comment = comment;
    }

    public void setDate(Date date1) {
        date.setTime(date1);
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

    public String getDate() {
        //int yy = date.get(Calendar.YEAR);
        //int mm = date.get(Calendar.MONTH);
        //int dd = date.get(Calendar.DAY_OF_MONTH);
        return date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DAY_OF_MONTH);
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
        return "Object: " + object + " | Count: " + current_counter + " | Comment: " + comment;
    }
}
