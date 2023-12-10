package com.example.todo_160420084.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_160420084.R
import com.example.todo_160420084.viewmodel.ToDoListViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*

class ToDoListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel:ToDoListViewModel
    private val todoListAdapter  = ToDoListAdapter(arrayListOf(), { item -> viewModel.clearTask(item) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)
        viewModel.refresh()
        recViewToDo.layoutManager = LinearLayoutManager(context)
        recViewToDo.adapter = todoListAdapter

        fabAddTodo.setOnClickListener {
            val action = ToDoListFragmentDirections.actionCreateToDo()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            var txtEmpty = view?.findViewById<TextView>(R.id.textEmpty)
            if(it.isEmpty()) {
                txtEmpty?.visibility = View.VISIBLE
            } else {
                txtEmpty?.visibility = View.GONE
            }
        })
    }
}