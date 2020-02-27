package com.manga4all.scrapper.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object HttpConnector {
    fun obtainDocument(url: String): Document = Jsoup.connect(url).get()
}