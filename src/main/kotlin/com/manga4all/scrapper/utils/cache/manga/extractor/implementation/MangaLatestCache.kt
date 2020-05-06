package com.manga4all.scrapper.utils.cache.manga.extractor.implementation

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSource
import com.manga4all.scrapper.utils.cache.Cache
import com.manga4all.scrapper.utils.cache.manga.extractor.GuavaBaseCache

class MangaLatestCache(val maxSize: Long,
                       val mangaSource: MangaSource,
                       val expirationTimeAfterAccessSeconds: Long) : Cache<Int, List<MangaInfo>> {

    private val cacheLoader = object : CacheLoader<Int, List<MangaInfo>>() {
        override fun load(id: Int): List<MangaInfo> {
            return mangaSource.getLatest(id)
        }
    }

    private val cache = GuavaBaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: Int): List<MangaInfo> {
        return cache?.get(id).orEmpty()
    }
}
