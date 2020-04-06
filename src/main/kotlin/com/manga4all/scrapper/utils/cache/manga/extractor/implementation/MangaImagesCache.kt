package com.manga4all.scrapper.utils.cache.manga.extractor.implementation

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.cache.Cache
import com.manga4all.scrapper.utils.cache.manga.extractor.GuavaBaseCache

class MangaImagesCache(val maxSize: Long,
                       val mangaSourceOperation: MangaSourceOperation,
                       val expirationTimeAfterAccessSeconds: Long) : Cache<MangaChapter, List<String>> {

    private val cacheLoader = object : CacheLoader<MangaChapter, List<String>>() {
        override fun load(id: MangaChapter): List<String> {
            return mangaSourceOperation.extractImagesUrl(id)
        }
    }

    private val cache = GuavaBaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: MangaChapter): List<String> {
        return cache?.get(id).orEmpty()
    }
}
