package com.vabasca.model;

public class ItemFactory {
    public ItemTemp getItem (int itemType) {
        if (itemType != 6)
            return new ToDoItemTemp();
        else
            return new GroceryItemTemp();
    }
}
