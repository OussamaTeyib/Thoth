package com.oussamateyib.thoth.core.domain.util

import org.junit.Assert.assertEquals
import org.junit.Test

class OrderTypeTest {
    @Test
    fun ascendingToggle_returnsDescending() {
        val orderType = OrderType.Ascending
        assertEquals(OrderType.Descending, orderType.toggle())
    }

    @Test
    fun descendingToggle_returnsAscending() {
        val orderType = OrderType.Descending
        assertEquals(OrderType.Ascending, orderType.toggle())
    }
}
