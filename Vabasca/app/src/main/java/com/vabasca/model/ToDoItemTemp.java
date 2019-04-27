package com.vabasca.model;

import java.util.Date;
import java.util.Calendar;

public class ToDoItemTemp extends ItemTemp {
    public ToDoItemTemp() {
        super();

        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();

        reminder = true;
        due = new Date(t + (5 * ONE_MINUTE_IN_MILLIS));
    }

    @Override
    public void setId(int newId) {

    }

    @Override
    public void setName(String newName) {
        this.name = newName;
    }

    @Override
    public void setDesciption(String newDescription) {
        this.desciption = newDescription;
    }

    @Override
    public void setQuantity(int newQuantity) {

    }

    @Override
    public void setReminder(boolean newReminder) {

    }

    @Override
    public void setDue(Date newDue) {
        due = newDue;
    }
}
