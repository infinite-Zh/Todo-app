package com.infinite.todoapp.data.source;

import com.infinite.todoapp.data.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskDataRepository implements TaskDataSource {

    private static TaskDataRepository sINSTANCE;
    private TaskDataSource mTasksRemoteDataSource;
    private TaskDataSource mTasksLocalDataSource;
    private Map<String, Task> mCache;
    private boolean isCacheDirty;

    private TaskDataRepository(TaskDataSource tasksRemoteDataSource, TaskDataSource tasksLocalDataSource) {
        mTasksLocalDataSource = tasksLocalDataSource;
        mTasksRemoteDataSource = tasksRemoteDataSource;
    }

    public static TaskDataRepository getInstance(TaskDataSource tasksRemoteDataSource, TaskDataSource tasksLocalDataSource) {
        if (sINSTANCE == null) {
            sINSTANCE = new TaskDataRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return sINSTANCE;
    }

    @Override
    public void getTasks(final LoadTasksCallback callback) {
        if (mCache != null&&!isCacheDirty) {
            callback.onTaskLoaded(new ArrayList<Task>(mCache.values()));
        } else {
            mTasksLocalDataSource.getTasks(new LoadTasksCallback() {
                @Override
                public void onTaskLoaded(List<Task> tasks) {
                    refreshCache(tasks);
                    callback.onTaskLoaded(tasks);

                }

                @Override
                public void onDataNotAvailable() {
                    getTaskDataFromRemoteDataSource(callback);
                }
            });
        }

    }

    @Override
    public void getTask(final String taskId, final GetTaskCallback callback) {
        mTasksLocalDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                getTaskFromRemoteDataSource(taskId,callback);
            }
        });
    }

    @Override
    public void saveTask(Task task) {
        mTasksLocalDataSource.saveTask(task);
        mTasksRemoteDataSource.saveTask(task);
        if (mCache==null){
            mCache=new LinkedHashMap<>();
        }
        mCache.put(task.getId(),task);

    }

    @Override
    public void completeTask(String taskId) {
        mTasksLocalDataSource.completeTask(taskId);
        isCacheDirty=true;
    }

    @Override
    public void activiteTask(String taskId) {
        mTasksLocalDataSource.activiteTask(taskId);
        isCacheDirty=true;
    }

    @Override
    public void completeTask(Task task) {

    }

    @Override
    public void clearCompleteTask() {
        mTasksLocalDataSource.clearCompleteTask();
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

    @Override
    public void updateTask(Task task) {
        mTasksLocalDataSource.updateTask(task);
        mTasksRemoteDataSource.updateTask(task);
    }

    private void getTaskDataFromRemoteDataSource(final LoadTasksCallback callback) {
        mTasksRemoteDataSource.getTasks(new LoadTasksCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                callback.onTaskLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void getTaskFromRemoteDataSource(String taskId, final GetTaskCallback callback) {
        mTasksRemoteDataSource.getTask(taskId, new GetTaskCallback() {
            @Override
            public void onTaskLoaded(Task task) {
                callback.onTaskLoaded(task);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Task> tasks){
        if (mCache==null){
            mCache=new LinkedHashMap<>();
        }
        mCache.clear();
        for(Task task:tasks){
            mCache.put(task.getId(),task);
        }
    }
}
