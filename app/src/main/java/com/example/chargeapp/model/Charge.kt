package com.example.chargeapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "charges")
data class Charge(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    private var title: String,
    private var description: String,
    private var cost: Float?,
    private var userId: Int): Parcelable {
        fun getUserId() = userId
        fun getTitle() = title
        fun getDescription() = description
        fun getCost() = cost
        fun getCostString(): String {
            return when(cost){
                null -> ""
                else -> "$cost ₾"
            }
        }
    }

// davit chinchaladze