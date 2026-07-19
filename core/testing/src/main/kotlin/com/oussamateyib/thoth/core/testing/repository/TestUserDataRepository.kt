package com.oussamateyib.thoth.core.testing.repository

import com.oussamateyib.thoth.core.data.repository.UserDataRepository
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

val emptyUserData = UserData(
    dynamicColor = true,
    darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
)

class TestUserDataRepository : UserDataRepository {
    private val _userData = MutableSharedFlow<UserData>(
        // Cache the latest emitted data for new collector
        replay = 1,
        // Discard the previous data if a new one arrives before it's collected
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    ).apply {
        tryEmit(emptyUserData)
    }

    private val currentUserData
        get() = _userData.replayCache.firstOrNull() ?: emptyUserData

    override val userData = _userData

    override suspend fun setDynamicColorPreference(dynamicColor: Boolean) {
        _userData.tryEmit(currentUserData.copy(dynamicColor = dynamicColor))
    }

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        _userData.tryEmit(currentUserData.copy(darkThemeConfig = darkThemeConfig))
    }
}
