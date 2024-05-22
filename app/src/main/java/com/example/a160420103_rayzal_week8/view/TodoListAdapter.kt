package com.example.a160420103_rayzal_week8.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160420103_rayzal_week8.databinding.TodoItemLayoutBinding
import com.example.a160420103_rayzal_week8.model.Todo
import com.example.a160420103_rayzal_week8.util.buildDb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TodoListAdapter(val todoList: ArrayList<Todo>, val onItemClick: (Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var binding: TodoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.checkTask.text = todoList[position].title

        holder.binding.checkTask.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                GlobalScope.launch {
                    val db = buildDb(holder.itemView.context)
                    db.todoDao().updateIsDone(1, todoList[position].uuid)
                }
            }
        }

        holder.binding.imgEdit.setOnClickListener {
            val uuid = todoList[position].uuid
            onItemClick(todoList[position])
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }
}