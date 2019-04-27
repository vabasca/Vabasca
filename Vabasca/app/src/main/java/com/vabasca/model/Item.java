package com.vabasca.model;

import java.util.Date;

public abstract class Item {
    static final long ONE_MINUTE_IN_MILLIS = 60000;//millisecs

    int id;
    String name;
    String desciption;
    int quantity;
    boolean reminder;
    Date due;

    public Item() {
        id = 0;
        name = "";
        desciption = "";
        quantity = 0;
        reminder = false;
        due = null;
    }

    public abstract void setId(int newId);
    public abstract void setName(String newName);
    public abstract void setDesciption(String newDescription);
    public abstract void setQuantity(int newQuantity);
    public abstract void setReminder(boolean newReminder);
    public abstract void setDue(Date newDue);

}
