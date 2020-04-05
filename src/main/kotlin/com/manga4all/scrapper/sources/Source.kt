package com.manga4all.scrapper.sources

import com.manga4all.scrapper.sources.manganelo.MangaNeloScrapper
import com.manga4all.scrapper.utils.JsoupHttpConnector

enum class Source(val scrapper: MangaSourceOperation) {
    MangaNelo(MangaNeloScrapper(JsoupHttpConnector))
}
