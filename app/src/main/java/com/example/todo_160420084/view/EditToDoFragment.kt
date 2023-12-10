package com.example.todo_160420084.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todo_160420084.R
import com.example.todo_160420084.databinding.FragmentEditToDoBinding
import com.example.todo_160420084.model.Todo
import com.example.todo_160420084.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

class EditToDoFragment : Fragment(),TodoSaveChangesClick, RadioClick {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_to_do, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        //textJudul.text = "Edit Todo"
        //btnAdd.text = "Save Changes"

        dataBinding.radioListener = this
        dataBinding.saveListener = this
        /*
        btnAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(editTitle.text.toString(), editNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
         */
    }
    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
        })
        /*
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            editTitle.setText(it.title)
            editNotes.setText(it.notes)
            when (it.priority) {
                1 -> radioLow.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioHigh.isChecked = true
            }
        })
         */
    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}