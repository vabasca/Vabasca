package com.vabasca.data.repositories;

import com.vabasca.data.TodoRepository;
import com.vabasca.data.network.model.TodoRequest;
import com.vabasca.data.repositories.local.TodoLocalData;
import com.vabasca.data.repositories.remote.TodoRemoteData;
import com.vabasca.data.model.Todo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;


/**
 * Created by Vinh & Tri on 04/19/2019
 */

public class TodoDataRepository implements TodoRepository {

    private TodoLocalData mTodoLocalData;
    private TodoRemoteData mTodoRemoteData;

    @Inject
    TodoDataRepository(TodoLocalData todoLocalData, TodoRemoteData todoRemoteData){
        mTodoLocalData = todoLocalData;
        mTodoRemoteData = todoRemoteData;
    }

    @Override
    public Observable<List<Todo>> getAllTodos() {
        return mTodoRemoteData.getAllTodos();
    }

    @Override
    public Completable saveTodo(TodoRequest todoRequest) {
        return mTodoRemoteData.saveTodo(todoRequest);
    }

    @Override
    public Completable removeTodoById(String id) {
        return mTodoRemoteData.removeTodoById(id);
    }

    @Override
    public Completable updateTodoById(String id) {
        return mTodoRemoteData.updateTodoById(id);
    }
}
