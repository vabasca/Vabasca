package com.vabasca.data.repositories.remote;

import com.vabasca.data.network.model.TodoRequest;
import com.vabasca.data.network.services.TodoService;
import com.vabasca.data.model.Todo;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by Vinh & Tri on 04/19/2019
 */

public class TodoRemoteData {

    private TodoService mTodoService;

    @Inject
    TodoRemoteData (TodoService todoService) {
        mTodoService = todoService;
    }

    public Observable<List<Todo>> getAllTodos() {
        return mTodoService.getTodos();
    }

    public Completable saveTodo(TodoRequest todoRequest){
        return mTodoService.saveTodo(todoRequest);
    }

    public Completable updateTodoById(String id){
        return mTodoService.updateTodoById(id);
    }

    public Completable removeTodoById(String id){
        return mTodoService.removeTodoById(id);
    }
}
