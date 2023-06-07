package com.bella.fitassistai.detail.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bella.fitassistai.user.UserModel
import com.bella.fitassistai.user.UserPreference
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel(){
    fun getUser(): LiveData<UserModel> {
        return pref.getUserData().asLiveData()
    }

    fun saveUserData(userData: UserModel){
        viewModelScope.launch {
            pref.saveUserToken(userData)
        }
    }

    fun login(){
        viewModelScope.launch {
            pref.login()
        }
    }
}