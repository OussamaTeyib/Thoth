package com.oussamateyib.thoth.core.domain

import com.oussamateyib.thoth.core.data.repository.UserDataRepository
import com.oussamateyib.thoth.core.model.data.DarkThemeConfig
import javax.inject.Inject

class SetDarkThemeConfigUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke(config: DarkThemeConfig) = userDataRepository.setDarkThemeConfig(config)
}