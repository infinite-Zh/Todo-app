package com.infinite.todoapp.taskList;

import com.infinite.todoapp.IBasePresenter;
import com.infinite.todoapp.IBaseView;
import com.infinite.todoapp.data.Task;

import java.util.List;

/**
 * Created by 19082 on 2017/6/7.
 */

public interface TaskListContract {

    interface View extends IBaseView<Presenter> {
        void showTaskList(List<Task> tasks);

        void showAddTask();

    }

    interface Presenter extends IBasePresenter {
        void addNewTask();

        void loadTasks(boolean forceUpdate);

        void completeTask(String taskId);

        void activteTask(String taskId);

        void refresh();

        void setFiltering(TaskFilterType taskFilterType);

        void clearCompleteTasks();
    }
}
