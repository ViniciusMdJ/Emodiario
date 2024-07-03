package com.emodiario.presentation.profile

import com.emodiario.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileUiState: UiState() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    fun updateName(name: String) {
        _name.value = name
    }

    private val _photoUrl = MutableStateFlow("")
    val photoUrl = _photoUrl.asStateFlow()

    fun updatePhotoUrl(photoUrl: String) {
        _photoUrl.value = photoUrl
    }

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    fun updateShowDialog(showDialog: Boolean) {
        _showDialog.value = showDialog
    }
}