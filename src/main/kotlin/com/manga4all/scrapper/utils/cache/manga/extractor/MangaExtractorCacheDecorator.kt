package com.manga4all.scrapper.utils.cache.manga.extractor

import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.cache.manga.extractor.implementation.*

class MangaExtractorCacheDecorator(private val mangaSourceOperation: MangaSourceOperation) : MangaSourceOperation {
    private val defaultMaxSize = 1000L
    private val defaultExpirationTimeAfterAccessSeconds = 6000L

    private val mangaImagesCache = MangaImagesCache(maxSize = defaultMaxSize,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = defaultExpirationTimeAfterAccessSeconds
    )

    private val mangaChapterCache = MangaChapterCache(
            maxSize = defaultMaxSize,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = defaultExpirationTimeAfterAccessSeconds
    )

    private val mangaLatestCache = MangaLatestCache(
            maxSize = 100,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = 300
    )

    private val mangaPopularCache = MangaPopularCache(
            maxSize = 100,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = 300
    )

    private val mangaSearchCache = MangaSearchCache(
            maxSize = 100,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = 300
    )

    override fun getFavorites(page: Int): List<MangaInfo> {
        return mangaSourceOperation.getFavorites(page)
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

    override fun extractChapterList(mangaId: String): List<MangaChapter> {
        return mangaChapterCache.extractItem(mangaId)
    }

    override fun extractImagesUrl(mangaChapter: MangaChapter): List<String> {
        return mangaImagesCache.extractItem(mangaChapter)
    }
}