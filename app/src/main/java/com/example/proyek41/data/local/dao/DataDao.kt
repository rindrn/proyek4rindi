package com.example.proyek41.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proyek41.data.local.entity.DataEntity

@Dao
interface DataDao {

    @Insert
    suspend fun insert(data: DataEntity)

    @Update
    suspend fun update(data: DataEntity)

    @Query("SELECT * FROM data_table ORDER BY id DESC")
    fun getAll(): LiveData<List<DataEntity>>

    @Query("SELECT * FROM data_table WHERE id = :dataId")
    suspend fun getById(dataId: Long): DataEntity?

    @Delete
    suspend fun delete(data: DataEntity)
}