package com.oussamateyib.thoth.feature.notes.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}