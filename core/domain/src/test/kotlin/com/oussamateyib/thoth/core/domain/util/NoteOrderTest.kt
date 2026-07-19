package com.oussamateyib.thoth.core.domain.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NoteOrderTest {
    @Test
    fun titleCopy_returnsTitleWithNewOrderType() {
        val order = NoteOrder.Title(OrderType.Ascending)
        val result = order.copy(OrderType.Descending)

        assertTrue(result is NoteOrder.Title)
        assertEquals(OrderType.Descending, result.orderType)
    }

    @Test
    fun dateCreatedCopy_returnsDateCreatedWithNewOrderType() {
        val order = NoteOrder.DateCreated(OrderType.Ascending)
        val result = order.copy(OrderType.Descending)

        assertTrue(result is NoteOrder.DateCreated)
        assertEquals(OrderType.Descending, result.orderType)
    }

    @Test
    fun dateUpdatedCopy_returnsDateUpdatedWithNewOrderType() {
        val order = NoteOrder.DateUpdated(OrderType.Ascending)
        val result = order.copy(OrderType.Descending)

        assertTrue(result is NoteOrder.DateUpdated)
        assertEquals(OrderType.Descending, result.orderType)
    }
}
