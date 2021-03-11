package com.jorfald.todokristiania.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorfald.todokristiania.database.dao.ToDoDAO
import com.jorfald.todokristiania.database.entities.ToDoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun getListWithStartingChar(toDoDAO: ToDoDAO, equalString: String, callback: (List<ToDoItem>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoItems = toDoDAO.getItemsEqualsTo(equalString)
            callback(toDoItems)
        }
    }

    fun updateList(toDoDAO: ToDoDAO, callback: (List<ToDoItem>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoItems = toDoDAO.getAllItems()
            callback(toDoItems)
        }
    }

    fun saveToDoItem(toDoDAO: ToDoDAO, text: String, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ToDoItem(title = text, isCompleted = false)
            toDoDAO.addToDoItem(newItem)

            callback()
        }
    }

    fun deleteItem(toDoDAO: ToDoDAO, itemToDelete: ToDoItem, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDAO.deleteToDoItem(itemToDelete)

            callback()
        }
    }

    fun changeItem(toDoDAO: ToDoDAO, itemToChange: ToDoItem, callback: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDAO.updateToDoItem(itemToChange)

            callback()
        }
    }
}