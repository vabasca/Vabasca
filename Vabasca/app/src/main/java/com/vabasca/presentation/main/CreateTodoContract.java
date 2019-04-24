package com.vabasca.presentation.main;

import com.vabasca.presentation.base.BaseView;

/**
 * Created by Vinh & Tri on 04/22/2019
 */

public interface CreateTodoContract {

    interface View extends BaseView{

        void setErrorTextField();

        void saveTodoFailure();

        void navigateToMainScreen();
    }

    interface Presenter{

        void saveTodo(String text);
    }
}
