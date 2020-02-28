package com.manga4all.scrapper.sources

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo


interface MangaSourceOperation {
    fun getFavorites(page: Int = 1): List<MangaInfo>
    fun searchPopular(page: Int = 1): List<MangaInfo>
    fun searchMangaRequest(query: String, page: Int = 1): List<MangaInfo>
    fun extractChapterList(manga: MangaInfo): List<MangaChapter>
    fun extractImagesUrl(title: String, chapter: MangaChapter): List<String>
}
