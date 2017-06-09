package com.infinite.todoapp.taskDetail;

import com.infinite.todoapp.IBasePresenter;
import com.infinite.todoapp.IBaseView;

/**
 * Created by 19082 on 2017/6/8.
 */

public interface TaskDetailContract {

    interface View extends IBaseView<Presenter>{
        void showTitle(String title);
        void showDescription(String description);
    }

    interface Presenter extends IBasePresenter{
        void deleteTask();
        void completeTask();
        void activiteTask();
    }
}
