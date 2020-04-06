package com.manga4all.scrapper.utils.cache

interface Cache<I, O> {
    fun extractItem(id: I): O
}