package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.UserDataRepository
import javax.inject.Inject

class GetUserPreferencesStreamUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
) {
    operator fun invoke() = userDataRepository.userData
}
