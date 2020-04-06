package com.manga4all.scrapper.manga.sources.manganelo

import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceOperation
import com.manga4all.scrapper.utils.http.connector.HttpConnector
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MangaNeloScrapper(val httpConnector: HttpConnector<Document>) : MangaSourceOperation {

    private val baseUrl = "https://manganelo.com"

    override fun searchPopular(page: Int): List<MangaInfo> {
        val docs = httpConnector.obtainDocument("$baseUrl/genre-all/$page?type=topview")

        return docs.getElementsByClass("panel-content-genres")
                .map { it.getElementsByClass("genres-item-img") }
                .flatMap { it.map { x -> x.getElementsByTag("a") } }
                .map { it.first() }
                .map(::extractMangaInfo)
    }

    override fun getLatest(page: Int): List<MangaInfo> {
        val docs = httpConnector.obtainDocument("$baseUrl/genre-all/$page")

        return docs.getElementsByClass("panel-content-genres")
                .map { it.getElementsByClass("content-genres-item") }
                .flatMap { it.map { x -> x.getElementsByTag("a") } }
                .map { it.first() }
                .map(::extractMangaInfo)
    }

    override fun getFavorites(page: Int): List<MangaInfo> {
        return listOf()
    }

    override fun searchMangaRequest(query: String, page: Int): List<MangaInfo> {
        val docs = httpConnector.obtainDocument("$baseUrl/search/$query?page=$page")

        return docs.getElementsByClass("search-story-item")
                .map { it.getElementsByClass("item-img") }
                .map { it.first() }
                .map(::extractMangaInfo)
    }

    override fun extractChapterList(mangaId: String): List<MangaChapter> {
        val docs = httpConnector.obtainDocument("$baseUrl/manga/$mangaId")
        return docs.getElementsByClass("row-content-chapter").first()
                ?.getElementsByTag("a")
                ?.map(::extractChapterInfo) ?: listOf()
    }

    override fun extractImagesUrl(mangaChapter: MangaChapter): List<String> {
        val docs: Document = httpConnector.obtainDocument(mangaChapter.url)

        val images: List<String> = docs.getElementsByClass("container-chapter-reader")
                .first()
                .getElementsByTag("img")
                .map { img -> img.attr("src") }

        return images
    }

    private fun extractMangaInfo(element: Element?): MangaInfo {

        val elementUrl = element?.attr("href") ?: ""

        val elementImg = element?.getElementsByTag("img")
                ?.first()
                ?.attr("src") ?: ""

        val elementTitle = element?.getElementsByTag("a")
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
