package com.manga4all.scrapper.sources.manganelo

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.HttpConnector
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

object MangaNeloScrapper : MangaSourceOperation {
    private val baseUrl = "https://manganelo.com"

    override fun searchPopular(page: Int): List<MangaInfo> {
        val docs = HttpConnector.obtainDocument("$baseUrl/genre-all/$page?type=topview")

        return docs.getElementsByClass("panel-content-genres")
                .map { extractMangaInfo(it, "genres-item-img") }
    }


    override fun getFavorites(page: Int): List<MangaInfo> {
        TODO("not implemented")
    }

    override fun searchMangaRequest(query: String, page: Int): List<MangaInfo> {
        val docs = HttpConnector.obtainDocument("$baseUrl/search/$query?page=$page")

        return docs.getElementsByClass("search-story-item")
                .map { extractMangaInfo(it, "item-img") }
    }

    override fun extractChapterList(manga: MangaInfo): List<MangaChapter> {
        val docs = HttpConnector.obtainDocument(manga.mangaUrl)
        return docs.getElementsByClass("row-content-chapter").first()
                ?.getElementsByTag("a")
                ?.map(::extractChapterInfo) ?: listOf()
    }

    override fun extractImagesUrl(title: String, chapter: MangaChapter): List<String> {
        val docs: Document = HttpConnector.obtainDocument("$baseUrl/$title/${chapter.number}")

        val images: List<String> = docs.getElementsByClass("container-chapter-reader")
                .first()
                .getElementsByTag("img")
                .map { img -> img.attr("src") }
                .filter { url -> url.contains("[0-9]".toRegex()) }

        return images
    }

    private fun extractMangaInfo(it: Element?, baseClassName: String): MangaInfo {
        val baseElement = it?.getElementsByClass(baseClassName)
                ?.first()

        val elementUrl = baseElement?.attr("href") ?: ""

        val elementImg = baseElement?.getElementsByTag("img")
                ?.first()
                ?.attr("src") ?: ""

        val elementTitle = baseElement?.getElementsByTag("a")
                ?.first()
                ?.attr("title") ?: ""

        val elementId = elementUrl.split("/").lastOrNull() ?: ""

        return MangaInfo(id = elementId, name = elementTitle, imageUrl = elementImg, mangaUrl = elementUrl)
    }

    private fun extractChapterInfo(it: Element?): MangaChapter {
        val link = it?.attr("href") ?: ""
        val title = it?.attr("title") ?: ""
        val chapterNumber = link.split("_").last()

        return MangaChapter(number = chapterNumber, name = title, url = link)
    }

}
