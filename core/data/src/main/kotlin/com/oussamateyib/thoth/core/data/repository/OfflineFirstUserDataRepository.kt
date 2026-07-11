package com.oussamateyib.thoth.core.data.repository

import com.oussamateyib.thoth.core.datastore.ThothPreferencesDataSource
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val thothPreferencesDataSource: ThothPreferencesDataSource
) : UserDataRepository {
    override val userData = thothPreferencesDataSource.userData

    override suspend fun setDynamicColorPreference(dynamicColor: Boolean) =
        thothPreferencesDataSource.setDynamicColorPreference(dynamicColor)

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) =
        thothPreferencesDataSource.setDarkThemeConfig(darkThemeConfig)
}