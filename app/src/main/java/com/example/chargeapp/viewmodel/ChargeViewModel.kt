package com.example.chargeapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.chargeapp.data.ChargeDatabase
import com.example.chargeapp.model.Charge
import com.example.chargeapp.model.User
import com.example.chargeapp.repository.ChargeRepository
import com.example.chargeapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChargeViewModel (application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<Charge>>
    val readAllUser: LiveData<List<User>>
    private val repository: ChargeRepository
    private val userRepository: UserRepository

    init {
        val chargeDao = ChargeDatabase.getDatabase(application).getChargeDao()
        val userDao = ChargeDatabase.getDatabase(application).getUserDao()

        repository = ChargeRepository(chargeDao)
        userRepository = UserRepository(userDao)

        readAllData = repository.readAllData
        readAllUser = userRepository.readAllUsers
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.addUser(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.updateUser(user)
        }
    }


    fun addCharge(charge: Charge){
        viewModelScope.launch(Dispatchers.IO){
            repository.addCharge(charge)
        }
    }
    fun updateCharge(charge: Charge){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateCharge(charge)
        }
    }
    fun deleteCharge(charge: Charge){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCharge(charge)
        }
    }
    fun deleteAllCharges(userId: Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllCharges(userId)
        }
    }
}