package com.example.chargeapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.chargeapp.model.Charge
import com.example.chargeapp.model.User


@Database(entities = [Charge::class, User::class], version = 1, exportSchema = false)
abstract class ChargeDatabase: RoomDatabase() {
    abstract fun getChargeDao(): ChargeDao
    abstract fun getUserDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: ChargeDatabase? = null

        fun getDatabase(context: Context): ChargeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room
                    .databaseBuilder(context.applicationContext, ChargeDatabase::class.java, "charge_and_user_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}