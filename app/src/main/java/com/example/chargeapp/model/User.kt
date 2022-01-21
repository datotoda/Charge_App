package com.example.chargeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    private var name: String,
    private var lastName: String,
    private var email: String,
    private var password: String): Parcelable {
        fun getName() = name
        fun getLastName() = lastName
        fun getEmail() = email
        fun getPassword() = password
    }

// davit chinchaladze