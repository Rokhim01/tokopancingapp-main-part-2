package com.example.tokopancingapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tokopancingapp.model.pancing
import kotlinx.coroutines.flow.Flow

@Dao
interface pancingdao {
    @Query("SELECT * FROM `pancing_table` ORDER BY name ASC")
    fun getAllpancing(): Flow<List<pancing>>


    @Insert
    suspend fun insertpancing(pancing: pancing)

    @Delete
    suspend fun deletepancing(pancing: pancing)

    @Update fun updatepancing(pancing: pancing)
}