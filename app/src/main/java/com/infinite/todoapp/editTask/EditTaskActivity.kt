package com.infinite.todoapp.editTask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.infinite.todoapp.R
import com.infinite.todoapp.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_edit_task.*

/**
 * Created by xz on 2018/2/7.
 */
class EditTaskActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)
        toolbar.title = "edit-task"
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val fragment=EditTaskFragment.Companion.getInstance()
        ActivityUtils.addFragmentToActivity(supportFragmentManager,fragment, R.id.contentFrame)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId==android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}