package com.oussamateyib.thoth.core.database.util

import androidx.room.TypeConverter
import kotlin.time.Instant

internal class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?) = value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun instantToLong(instant: Instant?) = instant?.toEpochMilliseconds()
}