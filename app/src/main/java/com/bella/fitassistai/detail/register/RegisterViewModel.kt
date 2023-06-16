package com.bella.fitassistai.detail.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bella.fitassistai.utils.UserModel
import com.bella.fitassistai.utils.UserPreference
import kotlinx.coroutines.launch

class RegisterViewModel(private val pref: UserPreference) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUserData(user)
        }
    }
}