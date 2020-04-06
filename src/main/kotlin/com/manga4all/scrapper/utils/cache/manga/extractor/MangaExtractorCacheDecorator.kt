package com.manga4all.scrapper.utils.cache.manga.extractor

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceOperation

class MangaExtractorCacheDecorator(val mangaSourceOperation: MangaSourceOperation) : MangaSourceOperation {
    private val mangaImagesCache = MangaImagesCache(
            maxSize = 10,
            mangaSourceOperation = mangaSourceOperation,
            expirationTimeAfterAccessSeconds = 6000
    )

    override fun getFavorites(page: Int): List<MangaInfo> {
        return mangaSourceOperation.getFavorites(page)
    }

    override fun getLatest(page: Int): List<MangaInfo> {
        return mangaSourceOperation.getLatest(page)
    }

    override fun searchPopular(page: Int): List<MangaInfo> {
        return mangaSourceOperation.searchPopular(page)
    }

    override fun searchMangaRequest(query: String, page: Int): List<MangaInfo> {
        return mangaSourceOperation.searchMangaRequest(query, page)
    }

    override fun extractChapterList(mangaId: String): List<MangaChapter> {
        return mangaSourceOperation.extractChapterList(mangaId)
    }

    override fun extractImagesUrl(mangaChapter: MangaChapter): List<String> {
        return mangaImagesCache.extractItem(mangaChapter)
    }
}