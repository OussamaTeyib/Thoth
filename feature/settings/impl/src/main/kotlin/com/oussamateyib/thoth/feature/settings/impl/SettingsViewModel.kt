package com.oussamateyib.thoth.feature.settings.impl

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.GetUserPreferencesStreamUseCase
import com.oussamateyib.thoth.core.domain.SetDarkThemeConfigUseCase
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import com.oussamateyib.thoth.core.ui.Language
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getUserPreferencesStreamUseCase: GetUserPreferencesStreamUseCase,
    private val setDarkThemeConfigUseCase: SetDarkThemeConfigUseCase
) : ViewModel() {
    private val _currentLanguage = MutableStateFlow(getCurrentLanguage())
    val currentLanguage = _currentLanguage.asStateFlow()

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

            is SettingsEvent.UpdateLanguage -> {
                updateLanguage(event.language)
            }
        }
    }

    private fun updateLanguage(language: Language) {
        val appLocale = if (language.tag.isEmpty()) {
            LocaleListCompat.getEmptyLocaleList()
        } else {
            LocaleListCompat.forLanguageTags(language.tag)
        }
        AppCompatDelegate.setApplicationLocales(appLocale)
        _currentLanguage.value = language
    }

    private fun getCurrentLanguage() = Language.fromTag(
        AppCompatDelegate.getApplicationLocales().toLanguageTags()
    )
}