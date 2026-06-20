package com.oussamateyib.thoth.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Immutable
data class ExtendedColorScheme(
    val butterYellow: ColorFamily,
    val peachCream: ColorFamily,
    val coralMist: ColorFamily,
    val roseBlush: ColorFamily,
    val lavender: ColorFamily,
    val periwinkle: ColorFamily,
    val skyBlue: ColorFamily,
    val mintGreen: ColorFamily,
    val sage: ColorFamily,
    val aquaMist: ColorFamily,
    val silverCloud: ColorFamily,
)

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

val extendedLight = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowLight,
        onButterYellowLight,
        butterYellowContainerLight,
        onButterYellowContainerLight,
    ),
    peachCream = ColorFamily(
        peachCreamLight,
        onPeachCreamLight,
        peachCreamContainerLight,
        onPeachCreamContainerLight,
    ),
    coralMist = ColorFamily(
        coralMistLight,
        onCoralMistLight,
        coralMistContainerLight,
        onCoralMistContainerLight,
    ),
    roseBlush = ColorFamily(
        roseBlushLight,
        onRoseBlushLight,
        roseBlushContainerLight,
        onRoseBlushContainerLight,
    ),
    lavender = ColorFamily(
        lavenderLight,
        onLavenderLight,
        lavenderContainerLight,
        onLavenderContainerLight,
    ),
    periwinkle = ColorFamily(
        periwinkleLight,
        onPeriwinkleLight,
        periwinkleContainerLight,
        onPeriwinkleContainerLight,
    ),
    skyBlue = ColorFamily(
        skyBlueLight,
        onSkyBlueLight,
        skyBlueContainerLight,
        onSkyBlueContainerLight,
    ),
    mintGreen = ColorFamily(
        mintGreenLight,
        onMintGreenLight,
        mintGreenContainerLight,
        onMintGreenContainerLight,
    ),
    sage = ColorFamily(
        sageLight,
        onSageLight,
        sageContainerLight,
        onSageContainerLight,
    ),
    aquaMist = ColorFamily(
        aquaMistLight,
        onAquaMistLight,
        aquaMistContainerLight,
        onAquaMistContainerLight,
    ),
    silverCloud = ColorFamily(
        silverCloudLight,
        onSilverCloudLight,
        silverCloudContainerLight,
        onSilverCloudContainerLight,
    ),
)

val extendedDark = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowDark,
        onButterYellowDark,
        butterYellowContainerDark,
        onButterYellowContainerDark,
    ),
    peachCream = ColorFamily(
        peachCreamDark,
        onPeachCreamDark,
        peachCreamContainerDark,
        onPeachCreamContainerDark,
    ),
    coralMist = ColorFamily(
        coralMistDark,
        onCoralMistDark,
        coralMistContainerDark,
        onCoralMistContainerDark,
    ),
    roseBlush = ColorFamily(
        roseBlushDark,
        onRoseBlushDark,
        roseBlushContainerDark,
        onRoseBlushContainerDark,
    ),
    lavender = ColorFamily(
        lavenderDark,
        onLavenderDark,
        lavenderContainerDark,
        onLavenderContainerDark,
    ),
    periwinkle = ColorFamily(
        periwinkleDark,
        onPeriwinkleDark,
        periwinkleContainerDark,
        onPeriwinkleContainerDark,
    ),
    skyBlue = ColorFamily(
        skyBlueDark,
        onSkyBlueDark,
        skyBlueContainerDark,
        onSkyBlueContainerDark,
    ),
    mintGreen = ColorFamily(
        mintGreenDark,
        onMintGreenDark,
        mintGreenContainerDark,
        onMintGreenContainerDark,
    ),
    sage = ColorFamily(
        sageDark,
        onSageDark,
        sageContainerDark,
        onSageContainerDark,
    ),
    aquaMist = ColorFamily(
        aquaMistDark,
        onAquaMistDark,
        aquaMistContainerDark,
        onAquaMistContainerDark,
    ),
    silverCloud = ColorFamily(
        silverCloudDark,
        onSilverCloudDark,
        silverCloudContainerDark,
        onSilverCloudContainerDark,
    ),
)

val extendedLightMediumContrast = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowLightMediumContrast,
        onButterYellowLightMediumContrast,
        butterYellowContainerLightMediumContrast,
        onButterYellowContainerLightMediumContrast,
    ),
    peachCream = ColorFamily(
        peachCreamLightMediumContrast,
        onPeachCreamLightMediumContrast,
        peachCreamContainerLightMediumContrast,
        onPeachCreamContainerLightMediumContrast,
    ),
    coralMist = ColorFamily(
        coralMistLightMediumContrast,
        onCoralMistLightMediumContrast,
        coralMistContainerLightMediumContrast,
        onCoralMistContainerLightMediumContrast,
    ),
    roseBlush = ColorFamily(
        roseBlushLightMediumContrast,
        onRoseBlushLightMediumContrast,
        roseBlushContainerLightMediumContrast,
        onRoseBlushContainerLightMediumContrast,
    ),
    lavender = ColorFamily(
        lavenderLightMediumContrast,
        onLavenderLightMediumContrast,
        lavenderContainerLightMediumContrast,
        onLavenderContainerLightMediumContrast,
    ),
    periwinkle = ColorFamily(
        periwinkleLightMediumContrast,
        onPeriwinkleLightMediumContrast,
        periwinkleContainerLightMediumContrast,
        onPeriwinkleContainerLightMediumContrast,
    ),
    skyBlue = ColorFamily(
        skyBlueLightMediumContrast,
        onSkyBlueLightMediumContrast,
        skyBlueContainerLightMediumContrast,
        onSkyBlueContainerLightMediumContrast,
    ),
    mintGreen = ColorFamily(
        mintGreenLightMediumContrast,
        onMintGreenLightMediumContrast,
        mintGreenContainerLightMediumContrast,
        onMintGreenContainerLightMediumContrast,
    ),
    sage = ColorFamily(
        sageLightMediumContrast,
        onSageLightMediumContrast,
        sageContainerLightMediumContrast,
        onSageContainerLightMediumContrast,
    ),
    aquaMist = ColorFamily(
        aquaMistLightMediumContrast,
        onAquaMistLightMediumContrast,
        aquaMistContainerLightMediumContrast,
        onAquaMistContainerLightMediumContrast,
    ),
    silverCloud = ColorFamily(
        silverCloudLightMediumContrast,
        onSilverCloudLightMediumContrast,
        silverCloudContainerLightMediumContrast,
        onSilverCloudContainerLightMediumContrast,
    ),
)

val extendedLightHighContrast = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowLightHighContrast,
        onButterYellowLightHighContrast,
        butterYellowContainerLightHighContrast,
        onButterYellowContainerLightHighContrast,
    ),
    peachCream = ColorFamily(
        peachCreamLightHighContrast,
        onPeachCreamLightHighContrast,
        peachCreamContainerLightHighContrast,
        onPeachCreamContainerLightHighContrast,
    ),
    coralMist = ColorFamily(
        coralMistLightHighContrast,
        onCoralMistLightHighContrast,
        coralMistContainerLightHighContrast,
        onCoralMistContainerLightHighContrast,
    ),
    roseBlush = ColorFamily(
        roseBlushLightHighContrast,
        onRoseBlushLightHighContrast,
        roseBlushContainerLightHighContrast,
        onRoseBlushContainerLightHighContrast,
    ),
    lavender = ColorFamily(
        lavenderLightHighContrast,
        onLavenderLightHighContrast,
        lavenderContainerLightHighContrast,
        onLavenderContainerLightHighContrast,
    ),
    periwinkle = ColorFamily(
        periwinkleLightHighContrast,
        onPeriwinkleLightHighContrast,
        periwinkleContainerLightHighContrast,
        onPeriwinkleContainerLightHighContrast,
    ),
    skyBlue = ColorFamily(
        skyBlueLightHighContrast,
        onSkyBlueLightHighContrast,
        skyBlueContainerLightHighContrast,
        onSkyBlueContainerLightHighContrast,
    ),
    mintGreen = ColorFamily(
        mintGreenLightHighContrast,
        onMintGreenLightHighContrast,
        mintGreenContainerLightHighContrast,
        onMintGreenContainerLightHighContrast,
    ),
    sage = ColorFamily(
        sageLightHighContrast,
        onSageLightHighContrast,
        sageContainerLightHighContrast,
        onSageContainerLightHighContrast,
    ),
    aquaMist = ColorFamily(
        aquaMistLightHighContrast,
        onAquaMistLightHighContrast,
        aquaMistContainerLightHighContrast,
        onAquaMistContainerLightHighContrast,
    ),
    silverCloud = ColorFamily(
        silverCloudLightHighContrast,
        onSilverCloudLightHighContrast,
        silverCloudContainerLightHighContrast,
        onSilverCloudContainerLightHighContrast,
    ),
)

val extendedDarkMediumContrast = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowDarkMediumContrast,
        onButterYellowDarkMediumContrast,
        butterYellowContainerDarkMediumContrast,
        onButterYellowContainerDarkMediumContrast,
    ),
    peachCream = ColorFamily(
        peachCreamDarkMediumContrast,
        onPeachCreamDarkMediumContrast,
        peachCreamContainerDarkMediumContrast,
        onPeachCreamContainerDarkMediumContrast,
    ),
    coralMist = ColorFamily(
        coralMistDarkMediumContrast,
        onCoralMistDarkMediumContrast,
        coralMistContainerDarkMediumContrast,
        onCoralMistContainerDarkMediumContrast,
    ),
    roseBlush = ColorFamily(
        roseBlushDarkMediumContrast,
        onRoseBlushDarkMediumContrast,
        roseBlushContainerDarkMediumContrast,
        onRoseBlushContainerDarkMediumContrast,
    ),
    lavender = ColorFamily(
        lavenderDarkMediumContrast,
        onLavenderDarkMediumContrast,
        lavenderContainerDarkMediumContrast,
        onLavenderContainerDarkMediumContrast,
    ),
    periwinkle = ColorFamily(
        periwinkleDarkMediumContrast,
        onPeriwinkleDarkMediumContrast,
        periwinkleContainerDarkMediumContrast,
        onPeriwinkleContainerDarkMediumContrast,
    ),
    skyBlue = ColorFamily(
        skyBlueDarkMediumContrast,
        onSkyBlueDarkMediumContrast,
        skyBlueContainerDarkMediumContrast,
        onSkyBlueContainerDarkMediumContrast,
    ),
    mintGreen = ColorFamily(
        mintGreenDarkMediumContrast,
        onMintGreenDarkMediumContrast,
        mintGreenContainerDarkMediumContrast,
        onMintGreenContainerDarkMediumContrast,
    ),
    sage = ColorFamily(
        sageDarkMediumContrast,
        onSageDarkMediumContrast,
        sageContainerDarkMediumContrast,
        onSageContainerDarkMediumContrast,
    ),
    aquaMist = ColorFamily(
        aquaMistDarkMediumContrast,
        onAquaMistDarkMediumContrast,
        aquaMistContainerDarkMediumContrast,
        onAquaMistContainerDarkMediumContrast,
    ),
    silverCloud = ColorFamily(
        silverCloudDarkMediumContrast,
        onSilverCloudDarkMediumContrast,
        silverCloudContainerDarkMediumContrast,
        onSilverCloudContainerDarkMediumContrast,
    ),
)

val extendedDarkHighContrast = ExtendedColorScheme(
    butterYellow = ColorFamily(
        butterYellowDarkHighContrast,
        onButterYellowDarkHighContrast,
        butterYellowContainerDarkHighContrast,
        onButterYellowContainerDarkHighContrast,
    ),
    peachCream = ColorFamily(
        peachCreamDarkHighContrast,
        onPeachCreamDarkHighContrast,
        peachCreamContainerDarkHighContrast,
        onPeachCreamContainerDarkHighContrast,
    ),
    coralMist = ColorFamily(
        coralMistDarkHighContrast,
        onCoralMistDarkHighContrast,
        coralMistContainerDarkHighContrast,
        onCoralMistContainerDarkHighContrast,
    ),
    roseBlush = ColorFamily(
        roseBlushDarkHighContrast,
        onRoseBlushDarkHighContrast,
        roseBlushContainerDarkHighContrast,
        onRoseBlushContainerDarkHighContrast,
    ),
    lavender = ColorFamily(
        lavenderDarkHighContrast,
        onLavenderDarkHighContrast,
        lavenderContainerDarkHighContrast,
        onLavenderContainerDarkHighContrast,
    ),
    periwinkle = ColorFamily(
        periwinkleDarkHighContrast,
        onPeriwinkleDarkHighContrast,
        periwinkleContainerDarkHighContrast,
        onPeriwinkleContainerDarkHighContrast,
    ),
    skyBlue = ColorFamily(
        skyBlueDarkHighContrast,
        onSkyBlueDarkHighContrast,
        skyBlueContainerDarkHighContrast,
        onSkyBlueContainerDarkHighContrast,
    ),
    mintGreen = ColorFamily(
        mintGreenDarkHighContrast,
        onMintGreenDarkHighContrast,
        mintGreenContainerDarkHighContrast,
        onMintGreenContainerDarkHighContrast,
    ),
    sage = ColorFamily(
        sageDarkHighContrast,
        onSageDarkHighContrast,
        sageContainerDarkHighContrast,
        onSageContainerDarkHighContrast,
    ),
    aquaMist = ColorFamily(
        aquaMistDarkHighContrast,
        onAquaMistDarkHighContrast,
        aquaMistContainerDarkHighContrast,
        onAquaMistContainerDarkHighContrast,
    ),
    silverCloud = ColorFamily(
        silverCloudDarkHighContrast,
        onSilverCloudDarkHighContrast,
        silverCloudContainerDarkHighContrast,
        onSilverCloudContainerDarkHighContrast,
    ),
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

val LocalExtendedColorScheme = staticCompositionLocalOf { extendedLight }

val MaterialTheme.extendedColorScheme: ExtendedColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalExtendedColorScheme.current

@Composable
fun ThothTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    val extendedColors = if (darkTheme) extendedDark else extendedLight

    CompositionLocalProvider(
        LocalExtendedColorScheme provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = ThothTypography,
            shapes = ThothShapes,
            content = content
        )
    }
}