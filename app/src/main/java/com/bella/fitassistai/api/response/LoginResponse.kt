package com.bella.fitassistai.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("accessToken")
    val accessToken: String,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)