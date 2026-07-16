package com.oussamateyib.thoth.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThothPreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    val userData = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserData(
                dynamicColor = preferences[PreferencesKeys.DYNAMIC_COLOR] ?: true,
                darkThemeConfig = when (preferences[PreferencesKeys.DARK_THEME_CONFIG]) {
                    null -> DarkThemeConfig.FOLLOW_SYSTEM
                    DarkThemeConfig.LIGHT.name -> DarkThemeConfig.LIGHT
                    DarkThemeConfig.DARK.name -> DarkThemeConfig.DARK
                    else -> DarkThemeConfig.FOLLOW_SYSTEM
                },
            )
        }

    suspend fun setDynamicColorPreference(dynamicColor: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DYNAMIC_COLOR] = dynamicColor
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME_CONFIG] = darkThemeConfig.name
        }
    }
}

private object PreferencesKeys {
    val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color_preference")
    val DARK_THEME_CONFIG = stringPreferencesKey("dark_theme_config")
}
