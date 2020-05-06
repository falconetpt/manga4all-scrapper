package com.manga4all.scrapper.utils.cache.manga.extractor.implementation

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSource
import com.manga4all.scrapper.utils.cache.Cache
import com.manga4all.scrapper.utils.cache.manga.extractor.GuavaBaseCache

class MangaChapterCache(val maxSize: Long,
                        val mangaSource: MangaSource,
                        val expirationTimeAfterAccessSeconds: Long) : Cache<MangaInfo, List<MangaChapter>> {

    private val cacheLoader = object : CacheLoader<MangaInfo, List<MangaChapter>>() {
        override fun load(id: MangaInfo): List<MangaChapter> {
            return mangaSource.extractChapterList(id)
        }
    }

    private val cache = GuavaBaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: MangaInfo): List<MangaChapter> {
        return cache?.get(id).orEmpty()
    }
}
