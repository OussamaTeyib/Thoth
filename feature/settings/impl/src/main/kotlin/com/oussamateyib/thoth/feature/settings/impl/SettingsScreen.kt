package com.oussamateyib.thoth.feature.settings.impl

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.oussamateyib.thoth.core.model.data.Language
import com.oussamateyib.thoth.core.model.data.UserData
import com.oussamateyib.thoth.core.ui.LanguageChooserDialog
import com.oussamateyib.thoth.core.ui.SettingsItem
import com.oussamateyib.thoth.core.ui.SettingsSectionTitle
import com.oussamateyib.thoth.core.ui.SettingsSwitchRow
import com.oussamateyib.thoth.core.ui.ThemeChooserDialog
import com.oussamateyib.thoth.core.ui.asLabel

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    onLicensesClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val userData by viewModel.userData.collectAsStateWithLifecycle()
    val currentLanguage by viewModel.currentLanguage.collectAsStateWithLifecycle()

    SettingsScreen(
        userData = userData,
        isDynamicColorSupported = viewModel.isDynamicColorSupported,
        currentLanguage = currentLanguage,
        appVersion = viewModel.appVersion,
        onBackClick = onBackClick,
        onLicensesClick = onLicensesClick,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(
    userData: UserData,
    isDynamicColorSupported: Boolean,
    currentLanguage: Language,
    appVersion: String,
    onBackClick: () -> Unit,
    onLicensesClick: () -> Unit,
    onEvent: (SettingsEvent) -> Unit
) {
    val verticalScroll = rememberScrollState()
    var showThemeDialog by remember { mutableStateOf(false) }
    var showLanguageDialog by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(R.drawable.feature_settings_impl_arrow_back),
                            contentDescription = stringResource(R.string.feature_settings_impl_back)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.feature_settings_impl_title),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(verticalScroll)
        ) {
            SettingsSectionTitle(text = stringResource(R.string.feature_settings_impl_display_options))
            SettingsSwitchRow(
                label = stringResource(R.string.feature_settings_impl_dynamic_theming),
                checked = if (isDynamicColorSupported) userData.dynamicColor else false,
                enabled = isDynamicColorSupported,
                onCheckedChange = { onEvent(SettingsEvent.SetDynamicColorPreference(it)) }
            )
            SettingsItem(
                label = stringResource(R.string.feature_settings_impl_theme),
                value = userData.darkThemeConfig.asLabel(),
                onClick = { showThemeDialog = true }
            )
            SettingsItem(
                label = stringResource(R.string.feature_settings_impl_language),
                value = currentLanguage.asLabel(),
                onClick = { showLanguageDialog = true }
            )

            HorizontalDivider(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp))

            SettingsSectionTitle(text = stringResource(R.string.feature_settings_impl_about))
            SettingsItem(
                label = stringResource(R.string.feature_settings_impl_app_version),
                value = appVersion,
                onClick = { }
            )
            SettingsItem(
                label = stringResource(R.string.feature_settings_impl_source_code),
                value = "GitHub",
                valueColor = MaterialTheme.colorScheme.primary,
                onClick = { uriHandler.openUri("https://github.com/OussamaTeyib/Thoth") },
                trailingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.feature_settings_impl_open_in_new),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
            SettingsItem(
                label = stringResource(R.string.feature_settings_impl_open_source_licenses),
                value = "",
                onClick = onLicensesClick
            )
        }
    }

    if (showThemeDialog) {
        ThemeChooserDialog(
            onDismiss = { showThemeDialog = false },
            currentConfig = userData.darkThemeConfig,
            onConfigSelected = {
                onEvent(SettingsEvent.UpdateDarkThemeConfig(it))
                showThemeDialog = false
            }
        )
    }

    if (showLanguageDialog) {
        LanguageChooserDialog(
            onDismiss = { showLanguageDialog = false },
            currentLanguage = currentLanguage,
            onLanguageSelected = {
                onEvent(SettingsEvent.UpdateLanguage(it))
                showLanguageDialog = false
            }
        )
    }
}
