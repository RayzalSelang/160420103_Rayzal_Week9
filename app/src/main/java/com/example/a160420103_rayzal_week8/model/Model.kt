package com.example.a160420103_rayzal_week8.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,

    @ColumnInfo(name="is_done")
    var is_done:Int = 0 // menggunakan INTEGER karena SQLite tidak memiliki tipe data BOOLEAN
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0

}

