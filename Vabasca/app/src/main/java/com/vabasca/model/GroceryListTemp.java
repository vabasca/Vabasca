package com.vabasca.model;

import java.util.ArrayList;
import java.util.List;

public class GroceryListTemp extends ListTemp {

    List<GroceryItemTemp> groceryList;

    public GroceryListTemp(){
        groceryList = new ArrayList<GroceryItemTemp>();
    }

    public void addItem(GroceryItemTemp newItem) {
        groceryList.add(newItem);
    }

    public void removeItem(GroceryItemTemp item) {
        groceryList.remove(item);
    }

    public void archive(GroceryItemTemp item) {
        removeItem(item);
    }
}
