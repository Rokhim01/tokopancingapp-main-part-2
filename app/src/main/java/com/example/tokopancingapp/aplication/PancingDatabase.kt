package com.example.tokopancingapp.aplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokopancingapp.dao.pancingdao
import com.example.tokopancingapp.model.pancing

@Database(entities = [pancing ::class], version = 1, exportSchema = false)
abstract class PancingDatabase: RoomDatabase() {
    abstract fun pancingdao(): pancingdao

    companion object {
        private var INSTANCE: PancingDatabase? = null
        fun getDatabase(Context: Context): PancingDatabase {
            return INSTANCE ?: synchronized( this) {
                val instance = Room.databaseBuilder(
                    Context.applicationContext,
                    PancingDatabase::class.java,
                    "Pancing_Database_1"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}