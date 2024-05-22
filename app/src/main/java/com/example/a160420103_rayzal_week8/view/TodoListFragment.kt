// TodoListFragment.kt
package com.example.a160420103_rayzal_week8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160420103_rayzal_week8.databinding.FragmentTodoListBinding
import com.example.a160420103_rayzal_week8.model.Todo
import com.example.a160420103_rayzal_week8.viewmodel.ListTodoViewModel

class TodoListFragment : Fragment() {
    private lateinit var binding: FragmentTodoListBinding
    private lateinit var viewModel: ListTodoViewModel
    private val todoListAdapter = TodoListAdapter(arrayListOf(), onItemClick = { todo ->
        val action = TodoListFragmentDirections.actionEditTodoFragment(todo.uuid)
        Navigation.findNavController(requireView()).navigate(action)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)

        binding.recViewTodo.layoutManager = LinearLayoutManager(requireContext())
        binding.recViewTodo.adapter = todoListAdapter

        binding.btnFab.setOnClickListener {
            val action = TodoListFragmentDirections.actionEditTodoFragment(-1)
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer { todos ->
            todoListAdapter.updateTodoList(todos)
            if (todos.isEmpty()) {
                binding.recViewTodo?.visibility = View.GONE
                binding.txtError.setText("Your todo still empty.")
            } else {
                binding.recViewTodo?.visibility = View.VISIBLE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading == false) {
                binding.progressLoad?.visibility = View.GONE
            } else {
                binding.progressLoad?.visibility = View.VISIBLE
            }
        })
        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer { isError ->
            if (isError == false) {
                binding.txtError?.visibility = View.GONE
            } else {
                binding.txtError?.visibility = View.VISIBLE
            }
        })
    }
}