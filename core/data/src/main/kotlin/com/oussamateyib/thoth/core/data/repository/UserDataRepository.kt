package com.oussamateyib.thoth.core.data.repository

import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}