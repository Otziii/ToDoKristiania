package com.jorfald.todokristiania.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.jorfald.todokristiania.R

class ToDoListView(context: Context) : ConstraintLayout(context) {

    private val deleteItemButton: ImageButton
    private val titleView: TextView
    private val doneSwitch: SwitchCompat

    init {
        val view: View = LayoutInflater.from(context).inflate(R.layout.to_do_list_item, this)

        deleteItemButton = view.findViewById(R.id.todo_delete_button)
        titleView = view.findViewById(R.id.todo_title)
        doneSwitch = view.findViewById(R.id.done_switch)
    }

    fun setTitle(text: String) {
        titleView.text = text
    }

    fun setDone(isDone: Boolean) {
        doneSwitch.isChecked = isDone
    }

    fun setDeleteButtonClickListener(listener: () -> Unit) {
        deleteItemButton.setOnClickListener {
            listener()
        }
    }

    fun setCompletedClickListener(listener: (Boolean) -> Unit) {
        doneSwitch.setOnCheckedChangeListener { _, _ ->
            listener(doneSwitch.isChecked)
        }
    }
}
