package com.infinite.todoapp.taskList;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataRepository;
import com.infinite.todoapp.data.source.TaskDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.infinite.todoapp.taskList.TaskFilterType.*;

/**
 * Created by 19082 on 2017/6/7.
 */

public class TaskListPresenter implements TaskListContract.Presenter {
    private TaskListContract.View mTaskListView;
    private TaskDataRepository mTaskDataRepository;
    private TaskFilterType mFilterType = NONE;

    public TaskListPresenter(TaskDataRepository taskDataRepository, TaskListContract.View view) {
        mTaskDataRepository = taskDataRepository;
        mTaskListView = view;
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
        mTaskListView.showProgress();
        mTaskDataRepository.getTasks(new TaskDataSource.LoadTasksCallback() {
            @Override
            public void onTaskLoaded(List<Task> tasks) {
                List<Task> showList = new ArrayList<>();

                for (Task task : tasks) {
                    switch (mFilterType) {
                        case NONE:
                            showList.add(task);
                            break;
                        case TYPE_ACTIVE:
                            if (task.isActive()) {
                                showList.add(task);
                            }
                            break;
                        case TYPE_COMPLETED:
                            if (task.isCompleted()) {
                                showList.add(task);
                            }
                            break;
                    }
                }
                mTaskListView.showTaskList(showList);
                mTaskListView.refreshComplete();
            }

            @Override
            public void onDataNotAvailable() {
                mTaskListView.refreshComplete();
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
        mFilterType = taskFilterType;
        loadTasks(true);
    }

    @Override
    public void clearCompleteTasks() {
        mTaskDataRepository.clearCompleteTask();
        loadTasks(true);
    }
}
