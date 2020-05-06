package com.manga4all.scrapper.manga.sources

import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo


interface MangaSource {
    fun getFavorites(page: Int = 1): List<MangaInfo>
    fun getLatest(page: Int = 1): List<MangaInfo>
    fun searchPopular(page: Int = 1): List<MangaInfo>
    fun searchMangaRequest(searchMangaRequest: SearchMangaRequest): List<MangaInfo>
    fun extractChapterList(mangaInfo: MangaInfo): List<MangaChapter>
    fun extractImagesUrl(mangaChapter: MangaChapter): List<String>
}
