package com.manga4all.scrapper.utils.cache.manga.extractor

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.cache.Cache

class MangaChapterCache(val maxSize: Long,
                        val mangaSourceOperation: MangaSourceOperation,
                        val expirationTimeAfterAccessSeconds: Long) : Cache<String, List<MangaChapter>> {

    private val cacheLoader = object : CacheLoader<String, List<MangaChapter>>() {
        override fun load(id: String): List<MangaChapter> {
            return mangaSourceOperation.extractChapterList(id)
        }
    }

    private val cache = BaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: String): List<MangaChapter> {
        return cache?.get(id).orEmpty()
    }
}
