package com.oussamateyib.thoth.feature.notes.impl.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}