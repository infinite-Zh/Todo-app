package com.infinite.todoapp.taskList;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataRepository;
import com.infinite.todoapp.data.source.TaskDataSource;

import java.util.List;

/**
 * Created by 19082 on 2017/6/7.
 */

public class TaskListPresenter implements TaskListContract.Presenter {
    private TaskListContract.View mTaskListView;
    private TaskDataRepository mTaskDataRepository;
    public TaskListPresenter(TaskDataRepository taskDataRepository,TaskListContract.View view){
        mTaskDataRepository=taskDataRepository;
        mTaskListView=view;
    }
    @Override
    public void start() {

    }

    @Override
    public void addNewTask() {
        mTaskListView.showAddTask();
    }

    @Override
    public void loadTasks(boolean forceUpdate) {
        mTaskDataRepository.getTasks(new TaskDataSource.LoadTasksCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                mTaskListView.showTaskList(tasks);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    @Override
    public void completeTask(String taskId) {
        mTaskDataRepository.completeTask(taskId);
        loadTasks(true);
    }

    @Override
    public void activteTask(String taskId) {
        mTaskDataRepository.activiteTask(taskId);
        loadTasks(true);
    }

    @Override
    public void refresh() {
        loadTasks(true);
    }

    @Override
    public void setFiltering(TaskFilterType taskFilterType) {

    }
}
