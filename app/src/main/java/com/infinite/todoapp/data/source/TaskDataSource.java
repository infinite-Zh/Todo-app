package com.infinite.todoapp.data.source;

import com.infinite.todoapp.data.Task;

import java.util.List;

/**
 * Created by 19082 on 2017/6/8.
 */

public interface TaskDataSource {

    interface LoadTasksCallback {
        void onTaskLoaded(List<Task> tasks);

        void onDataNotAvailable();
    }

    interface GetTaskCallback {
        void onTaskLoaded(Task task);

        void onDataNotAvailable();
    }

    void getTasks(LoadTasksCallback callback);

    void getTask(String taskId,GetTaskCallback  callback);

    void saveTask(Task task);

    void completeTask(String taskId);

    void activiteTask(String taskId);

    void completeTask(Task task);

    void clearCompleteTask();

    void refreshTasks();

    void deleteAllTasks();

    void deleteTask(String taskId);

}
