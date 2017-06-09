package com.infinite.todoapp.data.source.remote;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataSource;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskRemoteSource implements TaskDataSource {

    private static TaskRemoteSource sINSTANCE;
    public static TaskRemoteSource getInstance(){
        if (sINSTANCE==null){
            sINSTANCE=new TaskRemoteSource();
        }
        return sINSTANCE;
    }
    @Override
    public void getTasks(LoadTasksCallback callback) {

    }

    @Override
    public void getTask(String taskId, GetTaskCallback callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void saveTask(Task task) {

    }

    @Override
    public void completeTask(String taskId) {

    }

    @Override
    public void activiteTask(String taskId) {

    }

    @Override
    public void completeTask(Task task) {

    }

    @Override
    public void clearCompleteTask() {

    }

    @Override
    public void refreshTasks() {

    }

    @Override
    public void deleteAllTasks() {

    }

    @Override
    public void deleteTask(String taskId) {

    }
}
