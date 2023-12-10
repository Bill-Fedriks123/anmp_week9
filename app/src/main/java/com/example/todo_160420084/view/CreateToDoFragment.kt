
package com.example.todo_160420084.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todo_160420084.R
import com.example.todo_160420084.model.Todo
import com.example.todo_160420084.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

class CreateToDoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel:DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val txtTitle = view.findViewById<EditText>(R.id.editTitle)
            val txtNotes = view.findViewById<EditText>(R.id.editNotes)

            val is_done = 0
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), is_done)

            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}