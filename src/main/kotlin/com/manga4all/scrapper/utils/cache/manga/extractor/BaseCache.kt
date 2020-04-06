package com.manga4all.scrapper.utils.cache.manga.extractor

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.google.common.cache.LoadingCache
import java.util.concurrent.TimeUnit

class BaseCache<I, O>(maxSize: Long,
                      expirationTimeAfterAccessSeconds: Long,
                      cacheLoader: CacheLoader<I, O>) {
    private val cache = CacheBuilder.newBuilder()
            .maximumSize(maxSize)
            .expireAfterAccess(expirationTimeAfterAccessSeconds, TimeUnit.SECONDS)
            .build(cacheLoader)

    fun getCache(): LoadingCache<I, O>? {
        return cache
    }
}