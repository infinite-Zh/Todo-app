package com.infinite.todoapp.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.TaskDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19082 on 2017/6/8.
 */

public class TaskLocalSource implements TaskDataSource {
    private static TaskLocalSource sINSTANCE;
    private TaskDbHelper mDbHelper;

    private TaskLocalSource(Context context) {
        mDbHelper = new TaskDbHelper(context, TaskDbHelper.TASK_DB_NAME, null, TaskDbHelper.VERSION);
    }

    public static TaskLocalSource getInstance(Context context) {
        if (sINSTANCE == null) {
            sINSTANCE = new TaskLocalSource(context);
        }
        return sINSTANCE;
    }

    @Override
    public void getTasks(LoadTasksCallback callback) {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Cursor cursor = db.query(TaskDbHelper.TABLE_NAME, null, null, null, null, null, TaskDbHelper.ID);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(TaskDbHelper.ID));
                String title = cursor.getString(cursor.getColumnIndex(TaskDbHelper.TITLE));
                String description = cursor.getString(cursor.getColumnIndex(TaskDbHelper.DESCRIPTION));
                boolean complete = cursor.getInt(cursor.getColumnIndex(TaskDbHelper.COMPLETED)) == 1;
                Task task = new Task(title, description, id, complete);
                tasks.add(task);
            }
        }
        callback.onTaskLoaded(tasks);
    }

    @Override
    public void getTask(String taskId, GetTaskCallback callback) {

    }


    public void saveTask(Task task) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TaskDbHelper.TITLE, task.getTitle());
        cv.put(TaskDbHelper.DESCRIPTION, task.getDescription());
        cv.put(TaskDbHelper.COMPLETED, task.isCompleted());
        cv.put(TaskDbHelper.ID, task.getId());
        db.insert(TaskDbHelper.TABLE_NAME, null, cv);
        db.close();
    }

    @Override
    public void completeTask(String taskId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TaskDbHelper.COMPLETED, 1);
        db.update(TaskDbHelper.TABLE_NAME, cv, TaskDbHelper.ID + "=?", new String[]{taskId});
    }

    @Override
    public void activiteTask(String taskId) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TaskDbHelper.COMPLETED, 0);
        db.update(TaskDbHelper.TABLE_NAME, cv, TaskDbHelper.ID + "=?", new String[]{taskId});
    }

    @Override
    public void completeTask(Task task) {

    }

    @Override
    public void clearCompleteTask() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sql = "delete from " + TaskDbHelper.TABLE_NAME + " where " + TaskDbHelper.COMPLETED + " = 1";
        db.execSQL(sql);
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
