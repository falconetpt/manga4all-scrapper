package com.manga4all.scrapper.utils.http.connector

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object JsoupHttpConnector : HttpConnector<Document> {
    override fun obtainDocument(url: String): Document = Jsoup.connect(url).get()
}