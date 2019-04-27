package com.vabasca.model;

import java.util.Date;

public class GroceryItem extends Item {
    public  GroceryItem() {
        super();
        quantity = 1;
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
        this.quantity = newQuantity;
    }

    @Override
    public void setReminder(boolean newReminder) {

    }

    @Override
    public void setDue(Date newDue) {

    }
}
