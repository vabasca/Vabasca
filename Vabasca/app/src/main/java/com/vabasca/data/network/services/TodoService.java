package com.vabasca.data.network.services;

import com.vabasca.data.network.model.TodoRequest;
import com.vabasca.data.model.Todo;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Vinh & Tri on 04/15/2019
 */

public interface TodoService {

    @GET("todos")
    Observable<List<Todo>> getTodos();

    @POST("todos")
    Completable saveTodo(@Body TodoRequest todoRequest);

    @PATCH("todos/{id}")
    Completable updateTodoById(@Path("id") String id);

    @DELETE("todos/{id}")
    Completable removeTodoById(@Path("id") String id);
}
