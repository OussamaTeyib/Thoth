package com.oussamateyib.thoth.core.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()

    fun toggle(): OrderType = when (this) {
        is Ascending -> Descending
        is Descending -> Ascending
    }
}