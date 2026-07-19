package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.testing.repository.TestUserDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SetDarkThemeConfigUseCaseTest {
    private val userDataRepository = TestUserDataRepository()
    private val useCase = SetDarkThemeConfigUseCase(userDataRepository)

    @Test
    fun whenSetDarkThemeConfig_updatesRepository() = runTest {
        useCase(DarkThemeConfig.DARK)
        assertEquals(
            DarkThemeConfig.DARK,
            userDataRepository.userData.first().darkThemeConfig,
        )

        useCase(DarkThemeConfig.LIGHT)
        assertEquals(
            DarkThemeConfig.LIGHT,
            userDataRepository.userData.first().darkThemeConfig,
        )

        useCase(DarkThemeConfig.FOLLOW_SYSTEM)
        assertEquals(
            DarkThemeConfig.FOLLOW_SYSTEM,
            userDataRepository.userData.first().darkThemeConfig,
        )
    }
}
