package com.vabasca.presentation.main;

import com.vabasca.data.TodoRepository;
import com.vabasca.data.UserRepository;
import com.vabasca.di.ActivityScope;
import com.vabasca.data.model.Todo;
import com.vabasca.presentation.base.Presenter;
import com.vabasca.utils.ErrorUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

@ActivityScope
public class MainPresenter extends Presenter<MainContract.View>
        implements MainContract.Presenter {

    private TodoRepository mTodoRepository;
    private UserRepository mUserRepository;

    @Inject
    public MainPresenter(TodoRepository todoRepository, UserRepository userRepository,
                         CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        mUserRepository = userRepository;
        mTodoRepository = todoRepository;
    }

    @Override
    public void logout() {
        getCompositeDisposable()
                .add(mUserRepository.logout()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getView().navigateToAuthenticationScreen()));
    }

    @Override
    public void getTodos() {
        getView().showLoading();
        getCompositeDisposable()
                .add(mTodoRepository.getAllTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getTodosSuccess, this::getTodosFailure));
    }

    @Override
    public void updateTodo(String id) {
        getView().showLoading();
        getCompositeDisposable()
                .add(mTodoRepository.updateTodoById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateTodoSuccess, this::updateTodoFailure));
    }

    @Override
    public void removeTodo(String id) {
        getView().showLoading();
        getCompositeDisposable()
                .add(mTodoRepository.removeTodoById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::removeTodoSuccess, this::removeTodoFailure));
    }

    private void getTodosSuccess(List<Todo> todoList) {
        if (todoList.isEmpty()) {
            getView().hideLoading();
            getView().showEmpty();
        } else {
            getView().hideLoading();
            getView().showTodos(todoList);
        }
    }

    private void getTodosFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().getTodosFailure(ErrorUtils.getErrorMessage(error));
        error.printStackTrace();
    }


    private void updateTodoSuccess() {
        getView().hideLoading();
        getView().updateTodoSuccess();
    }

    private void updateTodoFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().updateTodoFailure(ErrorUtils.getErrorMessage(error));
    }

    private void removeTodoSuccess() {
        getView().hideLoading();
        getView().removeTodoSuccess();
    }

    private void removeTodoFailure(Throwable error) {
        error.printStackTrace();
        getView().hideLoading();
        getView().removeTodoFailure(ErrorUtils.getErrorMessage(error));
    }

}
