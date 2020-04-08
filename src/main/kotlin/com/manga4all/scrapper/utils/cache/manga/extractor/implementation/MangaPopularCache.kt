package com.manga4all.scrapper.utils.cache.manga.extractor.implementation

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.cache.Cache
import com.manga4all.scrapper.utils.cache.manga.extractor.GuavaBaseCache

class MangaPopularCache(val maxSize: Long,
                        val mangaSourceOperation: MangaSourceOperation,
                        val expirationTimeAfterAccessSeconds: Long) : Cache<Int, List<MangaInfo>> {

    private val cacheLoader = object : CacheLoader<Int, List<MangaInfo>>() {
        override fun load(id: Int): List<MangaInfo> {
            return mangaSourceOperation.searchPopular(id)
        }
    }

    private val cache = GuavaBaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: Int): List<MangaInfo> {
        return cache?.get(id).orEmpty()
    }
}
