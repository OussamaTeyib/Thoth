package com.oussamateyib.thoth.feature.settings.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.GetUserPreferencesStreamUseCase
import com.oussamateyib.thoth.core.domain.SetDarkThemeConfigUseCase
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getUserPreferencesStreamUseCase: GetUserPreferencesStreamUseCase,
    private val setDarkThemeConfigUseCase: SetDarkThemeConfigUseCase
) : ViewModel() {
    val userData = getUserPreferencesStreamUseCase()
        .stateIn(
            scope = viewModelScope,
            // Wait 5 seconds before stopping to handle configuration changes smoothly
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserData(darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM)
        )

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.UpdateDarkThemeConfig -> {
                viewModelScope.launch {
                    setDarkThemeConfigUseCase(event.config)
                }
            }
        }
    }
}