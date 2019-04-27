package com.vabasca.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ListTemp {

    int id = 0;
    String name = "";
    int itemType = 0;

    public ListTemp() {
        int id = 0;
        String name = "";
        int itemType = 0;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setItemType(int newItemType) {
        this.itemType = newItemType;
    }

    public int getId() {
        return this.id;
    }

    public  String getName() {
        return this.name;
    }

    public int getItemType() {
        return this.itemType;
    }
}
