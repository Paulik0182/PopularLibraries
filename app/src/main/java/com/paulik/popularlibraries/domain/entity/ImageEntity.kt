package com.paulik.popularlibraries.domain.entity

import java.util.*

data class ImageEntity(
    val id: String = UUID.randomUUID().toString(),
    val image: String
)
