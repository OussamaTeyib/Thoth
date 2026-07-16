package com.oussamateyib.thoth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.GetUserPreferencesStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getUserPreferencesStreamUseCase: GetUserPreferencesStreamUseCase
) : ViewModel() {
    val uiState = getUserPreferencesStreamUseCase().map {
        MainActivityState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityState.Loading,
        // Wait 5 seconds before stopping to handle configuration changes smoothly
        started = SharingStarted.WhileSubscribed(5_000)
    )
}
