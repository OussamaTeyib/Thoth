package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import com.oussamateyib.thoth.core.model.data.UserData
import com.oussamateyib.thoth.core.testing.repository.TestUserDataRepository
import com.oussamateyib.thoth.core.testing.repository.emptyUserData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserPreferencesStreamUseCaseTest {
    private val userDataRepository = TestUserDataRepository()
    private val useCase = GetUserPreferencesStreamUseCase(userDataRepository)

    @Test
    fun whenRepositoryIsNotEmpty_returnsExpectedData() = runTest {
        val expectedUserData = UserData(
            dynamicColor = false,
            darkThemeConfig = DarkThemeConfig.DARK,
        )
        userDataRepository.sendUserData(expectedUserData)

        val actualUserData = useCase().first()

        assertEquals(expectedUserData, actualUserData)
    }

    @Test
    fun whenRepositoryIsEmpty_returnsEmptyData() = runTest {
        val expectedUserData = emptyUserData

        val actualUserData = useCase().first()

        assertEquals(expectedUserData, actualUserData)
    }
}
