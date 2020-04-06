package com.manga4all.scrapper.utils.http.connector

interface HttpConnector<T> {
    fun obtainDocument(url: String): T
}