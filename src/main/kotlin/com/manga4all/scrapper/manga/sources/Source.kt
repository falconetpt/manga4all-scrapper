package com.manga4all.scrapper.manga.sources

import com.manga4all.scrapper.manga.sources.manganelo.MangaNeloScrapper
import com.manga4all.scrapper.utils.JsoupHttpConnector

enum class Source(val scrapper: MangaSourceOperation) {
    MangaNelo(MangaNeloScrapper(JsoupHttpConnector))
}
