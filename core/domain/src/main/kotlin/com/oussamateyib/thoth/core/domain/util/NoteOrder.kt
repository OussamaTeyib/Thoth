package com.oussamateyib.thoth.core.domain.util

sealed class NoteOrder(
    val orderType: OrderType,
) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class DateCreated(orderType: OrderType) : NoteOrder(orderType)
    class DateUpdated(orderType: OrderType) : NoteOrder(orderType)

    fun copy(orderType: OrderType = this.orderType) = when (this) {
        is Title -> Title(orderType)
        is DateCreated -> DateCreated(orderType)
        is DateUpdated -> DateUpdated(orderType)
    }
}
