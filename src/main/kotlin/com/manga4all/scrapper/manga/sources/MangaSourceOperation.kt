package com.manga4all.scrapper.manga.sources

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo


interface MangaSourceOperation {
    fun getFavorites(page: Int = 1): List<MangaInfo>
    fun getLatest(page: Int = 1): List<MangaInfo>
    fun searchPopular(page: Int = 1): List<MangaInfo>
    fun searchMangaRequest(query: String, page: Int = 1): List<MangaInfo>
    fun extractChapterList(mangaId: String): List<MangaChapter>
    fun extractImagesUrl(mangaId: String, chapter: String): List<String>
}
