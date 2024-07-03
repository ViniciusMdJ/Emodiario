package com.emodiario.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emodiario.domain.model.User
import com.emodiario.domain.use_cases.GetActivitiesUseCase
import com.emodiario.presentation.common.toMessageError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getActivitiesUseCase: GetActivitiesUseCase,
) : ViewModel() {
    val uiState = HomeUiState()

    fun getContent(user: User) {
        viewModelScope.launch{
            try {
                uiState.updateActivities(getActivitiesUseCase(user.id))
                uiState.setContent(user)
            } catch (e: HttpException) {
                Log.e("RegisterActivityViewModel", e.toMessageError(), e)
                uiState.setError(e.toMessageError())
            } catch (e: Exception) {
                Log.e("RegisterActivityViewModel", e.message.orEmpty(), e)
                uiState.setError(e.message.orEmpty())
            }
        }
    }

    fun getActivities(userId :Int){
        viewModelScope.launch{
            try {
                uiState.updateActivities(getActivitiesUseCase(userId))
            } catch (e: HttpException) {
                Log.e("RegisterActivityViewModel", e.toMessageError(), e)
                uiState.setError(e.toMessageError())
            } catch (e: Exception) {
                Log.e("RegisterActivityViewModel", e.message.orEmpty(), e)
                uiState.setError(e.message.orEmpty())
            }
        }
    }
}