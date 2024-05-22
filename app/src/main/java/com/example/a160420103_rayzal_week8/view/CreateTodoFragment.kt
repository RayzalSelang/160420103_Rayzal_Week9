package com.example.a160420103_rayzal_week8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160420103_rayzal_week8.R
import com.example.a160420103_rayzal_week8.databinding.FragmentCreateTodoBinding
import com.example.a160420103_rayzal_week8.model.Todo
import com.example.a160420103_rayzal_week8.viewmodel.DetailTodoViewModel

class CreateTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var binding: FragmentCreateTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
            val todo = Todo(
                binding.txtTitle.text.toString(),
                binding.txtNotes.text.toString(),
                radio.tag.toString().toInt()
            )
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}