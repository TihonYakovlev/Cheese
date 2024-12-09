package com.example.cheese.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class ProfileData(
    val id: String = "",
    val firstname: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val birthday: String = "",
    val attachments: List<Attachment> = emptyList()
)

data class Attachment(
    val type: String,
    val value: String
)

class ProfileViewModel : ViewModel(){

    val profileData = mutableStateOf(ProfileData())
    val isLoading = mutableStateOf(false)
    val showDeleteDialog = mutableStateOf(false)
    val showLogoutDialog = mutableStateOf(false)

    fun loadProfile() {}

    fun logout() {}

    fun deleteAccount() {}

    fun updateProfile() {}

}