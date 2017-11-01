package com.github.bassaer.lib.model

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by nakayama on 2017/11/01.
 */
class UserTest {

    @Test
    @Throws(Exception::class)
    fun checkPrimary() {
        val primaryUser = User(3, "primaryUser")
        val notPrimaryUser = User(1, "notPrimaryUser")
        assertEquals(primaryUser.isPrimary(), true)
        assertEquals(notPrimaryUser.isPrimary(), false)
    }
}