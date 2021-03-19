package com.jorfald.todokristiania.database.dao

import androidx.room.*
import com.jorfald.todokristiania.database.entities.ToDoItem

@Dao
interface ToDoDAO {
    @Delete
    fun deleteToDoItem(item: ToDoItem)

    @Update
    fun updateToDoItem(item: ToDoItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToDoItem(item: ToDoItem)

    @Query("SELECT * FROM ToDo")
    fun getAllItems(): List<ToDoItem>

    @Query("SELECT * FROM ToDo WHERE lower(title) LIKE (:equalString || '%')")
    fun getItemsStartingWith(equalString: String): List<ToDoItem>
}