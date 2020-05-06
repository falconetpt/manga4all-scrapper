package com.manga4all.scrapper.manga.sources

object MangaSourceExtractor {
    private val sources: List<Source> = Source.values().asList()

    fun getRandomSource(): MangaSource {
        return sources.random().scrapper
    }
}