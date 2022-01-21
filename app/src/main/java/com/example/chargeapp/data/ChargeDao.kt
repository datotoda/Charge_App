package com.example.chargeapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chargeapp.model.Charge

@Dao
interface ChargeDao {
    @Query("SELECT * FROM charges ORDER BY cost DESC")
    fun readAllData(): LiveData<List<Charge>>

    @Query("DELETE FROM charges WHERE userId = :userId")
    suspend fun deleteAllCharges(userId: Int)

    @Delete
    suspend fun deleteCharge(charge: Charge)

    @Update
    suspend fun updateCharge(charge: Charge)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharge(charge: Charge)
}