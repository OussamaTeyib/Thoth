package com.oussamateyib.thoth.core.ui.util

sealed class PaletteLayout {
    object Row : PaletteLayout()
    object Grid : PaletteLayout()
}