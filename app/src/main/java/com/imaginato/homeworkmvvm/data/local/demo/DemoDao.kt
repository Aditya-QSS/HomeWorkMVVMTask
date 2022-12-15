package com.imaginato.homeworkmvvm.data.local.demo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface DemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDemo(demo: List<Demo>)
}