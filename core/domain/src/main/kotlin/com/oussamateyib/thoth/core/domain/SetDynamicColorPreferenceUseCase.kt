package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.UserDataRepository
import javax.inject.Inject

class SetDynamicColorPreferenceUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    suspend operator fun invoke(dynamicColor: Boolean) =
        userDataRepository.setDynamicColorPreference(dynamicColor)
}
