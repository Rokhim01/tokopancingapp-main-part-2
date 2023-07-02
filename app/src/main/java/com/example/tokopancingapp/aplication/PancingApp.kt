package com.example.tokopancingapp.aplication

import android.app.Application
import com.example.tokopancingapp.repository.pancingrepository

class PancingApp: Application() {
    val database by lazy { PancingDatabase.getDatabase( this) }
    val repository by lazy { pancingrepository(database.pancingdao()) }
}