package com.oussamateyib.thoth.core.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

    fun toggle() = when (this) {
        is Ascending -> Descending
        is Descending -> Ascending
    }
}