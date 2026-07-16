package com.oussamateyib.thoth.core.model.data

enum class Language(
    val tag: String,
) {
    FOLLOW_SYSTEM(""),
    ENGLISH("en"),
    ARABIC("ar");

    companion object {
        fun fromTag(tag: String) = entries.find { it.tag == tag } ?: FOLLOW_SYSTEM
    }
}
