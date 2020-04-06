package com.manga4all.scrapper.manga.sources

import com.manga4all.scrapper.manga.sources.manganelo.MangaNeloScrapper
import com.manga4all.scrapper.utils.cache.manga.extractor.MangaExtractorCacheDecorator
import com.manga4all.scrapper.utils.http.connector.JsoupHttpConnector

enum class Source(val scrapper: MangaSourceOperation) {
    MangaNelo(MangaExtractorCacheDecorator(MangaNeloScrapper(JsoupHttpConnector)))
}
