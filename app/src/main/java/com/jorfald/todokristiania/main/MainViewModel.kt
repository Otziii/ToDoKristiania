package com.jorfald.todokristiania.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorfald.todokristiania.database.dao.ToDoDAO
import com.jorfald.todokristiania.database.entities.ToDoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val allToDoItems: MutableLiveData<List<ToDoItem>> = MutableLiveData()

    fun getListWithStartingChar(toDoDAO: ToDoDAO, equalString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoItems = toDoDAO.getItemsStartingWith(equalString)
            allToDoItems.postValue(toDoItems)
        }
    }

    fun fetchList(toDoDAO: ToDoDAO) {
        viewModelScope.launch(Dispatchers.IO) {
            val toDoItems = toDoDAO.getAllItems()
            allToDoItems.postValue(toDoItems)
        }
    }

    fun saveToDoItem(toDoDAO: ToDoDAO, text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ToDoItem(title = text, isCompleted = false)
            toDoDAO.addToDoItem(newItem)

            fetchList(toDoDAO)
        }
    }

    fun deleteItem(toDoDAO: ToDoDAO, itemToDelete: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDAO.deleteToDoItem(itemToDelete)

            fetchList(toDoDAO)
        }
    }

    fun changeItem(toDoDAO: ToDoDAO, itemToChange: ToDoItem) {
        viewModelScope.launch(Dispatchers.IO) {
            toDoDAO.updateToDoItem(itemToChange)

            fetchList(toDoDAO)
        }
    }
}