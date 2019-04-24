package com.vabasca.data;

import com.vabasca.data.network.model.TodoRequest;
import com.vabasca.data.model.Todo;

import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public interface TodoRepository {

    Completable removeTodoById(String id);

    Completable updateTodoById(String id);

    Observable<List<Todo>> getAllTodos();

    Completable saveTodo(TodoRequest todoRequest);

}
