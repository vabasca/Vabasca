package com.vabasca.presentation.main;

import com.vabasca.data.TodoRepository;
import com.vabasca.data.network.model.TodoRequest;
import com.vabasca.di.ActivityScope;
import com.vabasca.presentation.base.Presenter;
import com.vabasca.utils.ValidationUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

@ActivityScope
public class CreateTodoPresenter extends Presenter<CreateTodoContract.View>
        implements CreateTodoContract.Presenter{

    private TodoRepository mTodoRepository;

    @Inject
    public CreateTodoPresenter(TodoRepository todoRepository,
                               CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        mTodoRepository = todoRepository;
    }

    @Override
    public void saveTodo(String text) {
        if (!ValidationUtils.isValidText(text)){
            getView().setErrorTextField();
            return;
        }
        getView().showLoading();
        getCompositeDisposable()
                .add(mTodoRepository.saveTodo(new TodoRequest(text))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveTodoSuccess, this::saveTodoFailure));
    }

    private void saveTodoSuccess(){
        getView().hideLoading();
        getView().navigateToMainScreen();
    }

    private void saveTodoFailure(Throwable error){
        error.printStackTrace();
        getView().hideLoading();
        getView().saveTodoFailure();
    }
}
