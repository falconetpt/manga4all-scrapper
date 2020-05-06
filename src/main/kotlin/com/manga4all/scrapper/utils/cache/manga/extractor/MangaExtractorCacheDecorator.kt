package com.manga4all.scrapper.utils.cache.manga.extractor

import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSource
import com.manga4all.scrapper.utils.cache.manga.extractor.implementation.*

class MangaExtractorCacheDecorator(private val mangaSource: MangaSource) : MangaSource {
    private val defaultMaxSize = 1000L
    private val defaultExpirationTimeAfterAccessSeconds = 6000L

    private val mangaImagesCache = MangaImagesCache(maxSize = defaultMaxSize,
            mangaSource = mangaSource,
            expirationTimeAfterAccessSeconds = defaultExpirationTimeAfterAccessSeconds
    )

    private val mangaChapterCache = MangaChapterCache(
            maxSize = defaultMaxSize,
            mangaSource = mangaSource,
            expirationTimeAfterAccessSeconds = defaultExpirationTimeAfterAccessSeconds
    )

    private val mangaLatestCache = MangaLatestCache(
            maxSize = 100,
            mangaSource = mangaSource,
            expirationTimeAfterAccessSeconds = 300
    )

    private val mangaPopularCache = MangaPopularCache(
            maxSize = 100,
            mangaSource = mangaSource,
            expirationTimeAfterAccessSeconds = 300
    )

    private val mangaSearchCache = MangaSearchCache(
            maxSize = 100,
            mangaSource = mangaSource,
            expirationTimeAfterAccessSeconds = 300
    )

    override fun getFavorites(page: Int): List<MangaInfo> {
        return mangaSource.getFavorites(page)
    }

    override fun getLatest(page: Int): List<MangaInfo> {
        return mangaLatestCache.extractItem(page)
    }

    override fun searchPopular(page: Int): List<MangaInfo> {
        return mangaPopularCache.extractItem(page)
    }

    override fun searchMangaRequest(searchMangaRequest: SearchMangaRequest): List<MangaInfo> {
        return mangaSearchCache.extractItem(searchMangaRequest)
    }

    override fun extractChapterList(mangaInfo: MangaInfo): List<MangaChapter> {
        return mangaChapterCache.extractItem(mangaInfo)
    }

    override fun extractImagesUrl(mangaChapter: MangaChapter): List<String> {
        return mangaImagesCache.extractItem(mangaChapter)
    }
}