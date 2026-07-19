package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.testing.repository.TestUserDataRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class SetDynamicColorPreferenceUseCaseTest {
    private val userDataRepository = TestUserDataRepository()
    private val useCase = SetDynamicColorPreferenceUseCase(userDataRepository)

    @Test
    fun whenSetDynamicColorPreference_updatesRepository() = runTest {
        useCase(false)
        assertEquals(false, userDataRepository.userData.first().dynamicColor)

        useCase(true)
        assertEquals(true, userDataRepository.userData.first().dynamicColor)
    }
}
