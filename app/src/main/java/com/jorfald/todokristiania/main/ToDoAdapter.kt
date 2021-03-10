package com.jorfald.todokristiania.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jorfald.todokristiania.database.entities.ToDoItem
import com.jorfald.todokristiania.views.ToDoListView

class ToDoAdapter(
    private val deleteListener: (ToDoItem) -> Unit,
    private val completedListener: (ToDoItem) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    private var dataSet: List<ToDoItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ToDoListView(parent.context)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todoItem = dataSet[position]
        holder.view.setTitle(todoItem.title)
        holder.view.setDone(todoItem.isCompleted)

        holder.view.setDeleteButtonClickListener {
            deleteListener(todoItem)
        }

        holder.view.setCompletedClickListener {
            completedListener(
                todoItem.copy(isCompleted = it)
            )
        }
    }


    override fun getItemCount() = dataSet.size

    fun setData(newData: List<ToDoItem>) {
        dataSet = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: ToDoListView) : RecyclerView.ViewHolder(view)
}
