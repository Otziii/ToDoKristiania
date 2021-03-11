package com.jorfald.todokristiania.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jorfald.todokristiania.database.dao.ToDoDAO
import com.jorfald.todokristiania.database.entities.ToDoItem

@Database(entities = [ToDoItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDAO(): ToDoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}