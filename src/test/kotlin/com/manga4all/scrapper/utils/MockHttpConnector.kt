package com.manga4all.scrapper.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class MockHttpConnector(val html: String) : HttpConnector<Document> {
    override fun obtainDocument(url: String): Document {
        return Jsoup.parse(html)
    }

}