package com.vabasca.model;

import java.util.ArrayList;
import java.util.List;

public class ToDoListTemp extends ListTemp {

    List<ToDoItemTemp> toDolist;

    public ToDoListTemp(){
        toDolist = new ArrayList<ToDoItemTemp>();
    }

    public void addItem(ToDoItemTemp newItem) {
        toDolist.add(newItem);
    }

    public void removeItem(ToDoItemTemp item) {
        toDolist.remove(item);
    }

    public void archive(ToDoItemTemp item) {
        removeItem(item);
    }
}
