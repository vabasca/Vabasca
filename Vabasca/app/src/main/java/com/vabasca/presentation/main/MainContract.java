package com.vabasca.presentation.main;

import com.vabasca.data.model.Todo;
import com.vabasca.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

public interface MainContract {

    interface View extends BaseView{

        void showTodos(List<Todo> todoList);

        void getTodosFailure(String error);

        void updateTodoSuccess();

        void updateTodoFailure(String message);

        void removeTodoSuccess();

        void removeTodoFailure(String message);

        void showEmpty();

        void navigateToAuthenticationScreen();
    }

    interface Presenter{

        void getTodos();

        void updateTodo(String id);

        void removeTodo(String id);

        void logout();
    }
}
