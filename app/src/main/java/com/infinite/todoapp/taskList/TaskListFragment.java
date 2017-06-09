package com.infinite.todoapp.taskList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.infinite.todoapp.R;
import com.infinite.todoapp.addTask.AddTaskActivity;
import com.infinite.todoapp.data.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19082 on 2017/6/7.
 */

public class TaskListFragment extends Fragment implements TaskListContract.View{

    private ScrollChildSwipeRefreshLayout mLayout;
    private ListView mListView;
    private View mNoTasksView;
    private TasksAdapter mAdapter;
    private List<Task> tasks=new ArrayList<>();
    private TaskListContract.Presenter mPresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tasks_frag, container, false);
        mLayout= (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        mListView= (ListView) root.findViewById(R.id.tasks_list);
        mNoTasksView=root.findViewById(R.id.noTasks);
        mLayout= (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        mLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTasks(true);
            }
        });
        FloatingActionButton fab= (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addNewTask();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter=new TasksAdapter(tasks, new TaskItemListener() {
            @Override
            public void onTaskClick(Task clickedTask) {

            }

            @Override
            public void onCompleteTaskClick(Task completedTask) {
                mPresenter.completeTask(completedTask.getId());
            }

            @Override
            public void onActivateTaskClick(Task activatedTask) {
                mPresenter.activteTask(activatedTask.getId());
            }
        });
        mListView.setAdapter(mAdapter);
        mPresenter.loadTasks(true);

    }

    @Override
    public void setPresenter(TaskListContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showTaskList(List<Task> tasks) {
        if (tasks!=null){
            this.tasks.clear();
            this.tasks.addAll(tasks);
            mAdapter.notifyDataSetChanged();
            mNoTasksView.setVisibility(View.GONE);
        }else {

        }

        mLayout.setRefreshing(false);
    }

    @Override
    public void showAddTask() {
        Intent intent=new Intent(getActivity(), AddTaskActivity.class);
        startActivity(intent);
    }

    private static class TasksAdapter extends BaseAdapter {

        private List<Task> mTasks;
        private TaskItemListener mItemListener;

        public TasksAdapter(List<Task> tasks, TaskItemListener itemListener) {
            setList(tasks);
            mItemListener = itemListener;
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }

        private void setList(List<Task> tasks) {
            mTasks = tasks;
        }

        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Task getItem(int i) {
            return mTasks.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = view;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
                rowView = inflater.inflate(R.layout.task_item, viewGroup, false);
            }

            final Task task = getItem(i);

            TextView titleTV = (TextView) rowView.findViewById(R.id.title);
            titleTV.setText(task.getTitleForList());

            CheckBox completeCB = (CheckBox) rowView.findViewById(R.id.complete);

            // Active/completed task UI
            completeCB.setChecked(task.isCompleted());
            if (task.isCompleted()) {
                rowView.setBackgroundDrawable(viewGroup.getContext()
                        .getResources().getDrawable(R.drawable.list_completed_touch_feedback));
            } else {
                rowView.setBackgroundDrawable(viewGroup.getContext()
                        .getResources().getDrawable(R.drawable.touch_feedback));
            }

            completeCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!task.isCompleted()) {
                        mItemListener.onCompleteTaskClick(task);
                    } else {
                        mItemListener.onActivateTaskClick(task);
                    }
                }
            });

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemListener.onTaskClick(task);
                }
            });

            return rowView;
        }
    }

    public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

        void onCompleteTaskClick(Task completedTask);

        void onActivateTaskClick(Task activatedTask);
    }

    public static TaskListFragment newInstance(){
        return new TaskListFragment();
    }
}
