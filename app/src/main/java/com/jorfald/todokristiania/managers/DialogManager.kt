package com.jorfald.todokristiania.managers

import android.content.Context
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class DialogManager {
    companion object {
        fun showInputDialog(context: Context, title: String, callback: (String) -> Unit) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle(title)

            val input = EditText(context)
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK") { _, _ ->
                callback(input.text.toString())
            }

            builder.setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

            builder.show()
        }
    }
}