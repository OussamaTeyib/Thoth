package com.oussamateyib.thoth.core.testing.repository

import com.oussamateyib.thoth.core.data.repository.UserDataRepository
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

val emptyUserData = UserData(
    dynamicColor = true,
    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
)

class TestUserDataRepository : UserDataRepository {
    private val _userData = MutableStateFlow(emptyUserData)

    override val userData = _userData

    override suspend fun setDynamicColorPreference(dynamicColor: Boolean) {
        _userData.update { it.copy(dynamicColor = dynamicColor) }
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        _userData.update { it.copy(darkThemeConfig = darkThemeConfig) }
    }

    // A test-only API to inject data directly
    fun sendUserData(userData: UserData) {
        _userData.value = userData
    }
}
