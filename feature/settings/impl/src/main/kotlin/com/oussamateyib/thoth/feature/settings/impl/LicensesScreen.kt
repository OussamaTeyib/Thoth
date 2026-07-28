package com.oussamateyib.thoth.feature.settings.impl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.util.withContext
import com.oussamateyib.thoth.core.designsystem.R as DesignR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LicensesScreen(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val libs = remember { Libs.Builder().withContext(context).build() }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(DesignR.drawable.core_designsystem_ic_arrow_back),
                            contentDescription = stringResource(DesignR.string.core_designsystem_back),
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.feature_settings_impl_open_source_licenses),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        },
    ) { innerPadding ->
        LibrariesContainer(
            libraries = libs,
            modifier = Modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}
