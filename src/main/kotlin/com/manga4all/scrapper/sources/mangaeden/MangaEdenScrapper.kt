package com.manga4all.scrapper.sources.mangaeden

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.sources.MangaSourceOperation

object MangaEdenScrapper : MangaSourceOperation {
    override fun searchPopular(page: Int): List<MangaInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLatest(page: Int): List<MangaInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavorites(page: Int): List<MangaInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchMangaRequest(query: String, page: Int): List<MangaInfo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun extractChapterList(mangaId: String): List<MangaChapter> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun extractImagesUrl(mangaId: String, chapter: String): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}