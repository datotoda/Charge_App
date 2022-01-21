package com.example.chargeapp.repository

import androidx.lifecycle.LiveData
import com.example.chargeapp.data.ChargeDao
import com.example.chargeapp.model.Charge

class ChargeRepository (private val chargeDao: ChargeDao){
    val readAllData: LiveData<List<Charge>> = chargeDao.readAllData()

    suspend fun addCharge(charge: Charge){
        chargeDao.addCharge(charge)
    }

    suspend fun updateCharge(charge: Charge){
        chargeDao.updateCharge(charge)
    }

    suspend fun deleteCharge(charge: Charge){
        chargeDao.deleteCharge(charge)
    }

    suspend fun deleteAllCharges(userId: Int){
        chargeDao.deleteAllCharges(userId)
    }
}