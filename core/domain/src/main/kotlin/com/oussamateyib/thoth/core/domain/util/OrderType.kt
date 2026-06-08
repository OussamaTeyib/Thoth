package com.oussamateyib.thoth.core.domain.util

sealed class OrderType {
    object Ascending : OrderType()
    object Descending : OrderType()
}