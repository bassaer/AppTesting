package com.github.bassaer.lib.model

/**
 * User
 * Created by nakayama on 2017/11/01.
 */
data class User(var id: Int,val name: String) {
    private var checksum = 0

    init {
        checksum = this.id * this.name.length
    }
    fun isPrimary() = this.id % 3 == 0

    public fun getChecksum() = this.checksum;
}