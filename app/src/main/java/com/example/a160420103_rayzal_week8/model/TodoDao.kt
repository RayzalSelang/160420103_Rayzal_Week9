package com.example.a160420103_rayzal_week8.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id: String): Todo

    @Query("UPDATE todo SET is_done=:isDone WHERE uuid = :id")
    suspend fun updateIsDone(isDone:Int, id:Int)

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    suspend fun update(title: String, notes: String, priority: Int, id: String)


    @Delete
    fun deleteTodo(todo:Todo)
}
