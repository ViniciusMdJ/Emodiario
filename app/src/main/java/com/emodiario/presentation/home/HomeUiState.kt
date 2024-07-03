package com.emodiario.presentation.home

import com.emodiario.domain.model.Activity
import com.emodiario.domain.model.User
import com.emodiario.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeUiState: UiState() {
    val imageUrl = MutableStateFlow<String?>(null)

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    private val _activities = MutableStateFlow<List<Activity>>(emptyList())
    val activities = _activities.asStateFlow()

    fun updateActivities(activities: List<Activity>) {
        _activities.value = activities
    }

    fun setContent(user: User) {
        _user.value = user
        super.setContent()
    }
}