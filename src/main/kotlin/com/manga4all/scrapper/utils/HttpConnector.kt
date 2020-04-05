package com.manga4all.scrapper.utils

interface HttpConnector<T> {
    fun obtainDocument(url: String): T
}