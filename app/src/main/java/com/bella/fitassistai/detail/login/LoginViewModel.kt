package com.bella.fitassistai.detail.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bella.fitassistai.utils.UserModel
import com.bella.fitassistai.utils.UserPreference
import com.bella.fitassistai.utils.UserToken
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: UserPreference) : ViewModel() {

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun login() {
        viewModelScope.launch {
            pref.login()
        }
    }

    fun saveUserData(userData: UserToken) {
        viewModelScope.launch {
            pref.saveUserToken(userData)
        }
    }

}