package com.carlos.network.models

data class Pokemon(
    var page: Int = 0,
    val url: String,
    val name: String,
    var color: Int = -7839624
) {
}