package com.github.bassaer.lib.util


import com.github.bassaer.lib.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * User validator test
 * Created by nakayama on 2017/11/01.
 */
class UserValidatorTest {
    @Test
    @Throws(Exception::class)
    fun validatorIsCorrect() {
        val validUser = User(10, "abcd")
        val invalidUser = User(2, "abcd")
        invalidUser.id = 3
        assertEquals(UserValidator.validate(validUser), true)
        assertEquals(UserValidator.validate(invalidUser), true)
    }
}