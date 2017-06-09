package com.infinite.todoapp.addTask;

import com.infinite.todoapp.IBasePresenter;
import com.infinite.todoapp.IBaseView;

/**
 * Created by 19082 on 2017/6/7.
 */

public interface AddTaskContract {

    interface View extends IBaseView<Presenter> {
        void showTaskList();

        void showEmptyTaskError();

        void setTitle(String title);

        void setDescription(String description);
    }

    interface Presenter extends IBasePresenter {
        void saveTask(String title, String description);

        void populateTask();

        boolean isDataMissing();
    }
}
