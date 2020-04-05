package com.manga4all.scrapper.sources

import com.manga4all.scrapper.sources.manganelo.MangaNeloScrapper

enum class Source(val scrapper: MangaSourceOperation) {
    MangaNelo(MangaNeloScrapper)
}
