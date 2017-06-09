package com.infinite.todoapp.data.source;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.infinite.todoapp.data.Task;
import com.infinite.todoapp.data.source.local.TaskLocalSource;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Created by 19082 on 2017/6/9.
 */

public class MyTaskLoader extends AsyncTaskLoader<List<Task>> {
    private SoftReference<Context> sr=null;
    public MyTaskLoader(Context context) {
        super(context);
        sr=new SoftReference<>(context);
    }

    @Override
    public List<Task> loadInBackground() {
        TaskLocalSource source=TaskLocalSource.getInstance(sr.get().getApplicationContext());
        return source.getTasks();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
