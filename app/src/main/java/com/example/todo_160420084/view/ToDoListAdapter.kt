package com.example.todo_160420084.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_160420084.R
import com.example.todo_160420084.databinding.LayoutTodoItemBinding
import com.example.todo_160420084.model.Todo
import kotlinx.android.synthetic.main.layout_todo_item.view.*

class ToDoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    :RecyclerView.Adapter<ToDoListAdapter.TodoViewHolder>(),
    TodoCheckedChangeListener, TodoEditClick
{
    class TodoViewHolder(var view:LayoutTodoItemBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = LayoutTodoItemBinding.inflate(inflater, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.checkboxListener = this
        holder.view.editListener = this
        /*
        val item = todoList[position]
        with(holder.view){
            val priority = when(item.priority) {
                1 -> "Low"
                2 -> "Medium"
                else -> "High"
            }
            checkTask.text = "[$priority] ${todoList[position].title}"
            checkTask.setOnCheckedChangeListener { compoundButton, b ->
                if (b) adapterOnClick(item)
            }
            buttonEdit.setOnClickListener {
                val action = ToDoListFragmentDirections.actionEditToDo(item.uuid)
                Navigation.findNavController(it).navigate(action)
            }
        }
         */
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = ToDoListFragmentDirections.actionEditToDo(uuid)
        Navigation.findNavController(v).navigate(action)
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}
