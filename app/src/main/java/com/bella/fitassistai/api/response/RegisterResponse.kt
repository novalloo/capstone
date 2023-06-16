package com.bella.fitassistai.api.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("RegisterResult")
    val registerResult: RegisterResult,

    @field: SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)
data class RegisterResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("email")
    val email: String
)