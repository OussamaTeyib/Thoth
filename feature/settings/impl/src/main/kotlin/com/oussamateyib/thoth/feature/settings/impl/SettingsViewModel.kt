package com.oussamateyib.thoth.feature.settings.impl

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oussamateyib.thoth.core.domain.GetUserPreferencesStreamUseCase
import com.oussamateyib.thoth.core.domain.SetDarkThemeConfigUseCase
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.Language
import com.oussamateyib.thoth.core.model.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    getUserPreferencesStreamUseCase: GetUserPreferencesStreamUseCase,
    private val setDarkThemeConfigUseCase: SetDarkThemeConfigUseCase
) : ViewModel() {
    val packageInfo: PackageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.packageManager.getPackageInfo(
            context.packageName,
            PackageManager.PackageInfoFlags.of(0)
        )
    } else {
        context.packageManager.getPackageInfo(context.packageName, 0)
    }
    val appVersion = packageInfo.versionName ?: ""

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