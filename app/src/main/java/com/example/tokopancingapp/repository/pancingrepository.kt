package com.example.tokopancingapp.repository

import com.example.tokopancingapp.dao.pancingdao
import com.example.tokopancingapp.model.pancing
import kotlinx.coroutines.flow.Flow

class pancingrepository(private val pancingdao: pancingdao) {
    val allpancing: Flow<List<pancing>> = pancingdao.getAllpancing()
    suspend fun insertpancing(pancing: pancing){
        pancingdao.insertpancing(pancing)
    }

    suspend fun deletepancing(pancing: pancing) {
        pancingdao.deletepancing(pancing)
    }

    suspend fun updatepancing(pancing: pancing) {
        pancingdao.updatepancing(pancing)
    }
}