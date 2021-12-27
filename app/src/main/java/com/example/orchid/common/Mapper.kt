package com.example.orchid.common

interface Mapper<T, U> {
    fun map(model: T): U
}