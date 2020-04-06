package com.manga4all.scrapper.utils.cache.manga.extractor.implementation

import com.google.common.cache.CacheLoader
import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.cache.Cache
import com.manga4all.scrapper.utils.cache.manga.extractor.GuavaBaseCache

class MangaSearchCache(maxSize: Long,
                       mangaSourceOperation: MangaSourceOperation,
                       expirationTimeAfterAccessSeconds: Long) : Cache<SearchMangaRequest, List<MangaInfo>> {

    private val cacheLoader = object : CacheLoader<SearchMangaRequest, List<MangaInfo>>() {
        override fun load(id: SearchMangaRequest): List<MangaInfo> {
            return mangaSourceOperation.searchMangaRequest(id)
        }
    }

    private val cache = GuavaBaseCache(maxSize, expirationTimeAfterAccessSeconds, cacheLoader).getCache()

    override fun extractItem(id: SearchMangaRequest): List<MangaInfo> {
        return cache?.get(id).orEmpty()
    }
}
