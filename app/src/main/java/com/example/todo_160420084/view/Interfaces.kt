package com.example.todo_160420084.view

import android.view.View
import android.widget.CompoundButton
import com.example.todo_160420084.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckedChanged(cb: CompoundButton,
                         isChecked:Boolean,
                         obj: Todo)
}

interface TodoEditClick {
    fun onTodoEditClick(v: View)
}

interface RadioClick {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}

interface TodoSaveChangesClick {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}