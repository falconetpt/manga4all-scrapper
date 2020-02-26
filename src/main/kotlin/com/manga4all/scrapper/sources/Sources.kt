package com.manga4all.scrapper.sources

import com.manga4all.scrapper.sources.mangaeden.MangaEdenScrapper

enum class Sources(val scrapper: MangaSourceOperation) {
    MangaEden(MangaEdenScrapper)
}
