package com.manga4all.scrapper.manga.sources.manganelo

import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.utils.MockHttpConnector
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class MangaNeloScrapperTest {
    private val readHtmlFile = { file: String -> File(file).readText(Charsets.UTF_8) }

    @Test
    fun searchPopular() {
        val html = readHtmlFile("src/test/resources/manga.sources.manganelo/popular.html")
        val httpConnector = MockHttpConnector(html)
        val result = MangaNeloScrapper(httpConnector).searchPopular(1)

        val expected = listOf(
                MangaInfo(
                        id = "hyer5231574354229",
                        name = "Tales of Demons and Gods",
                        imageUrl = "https://avt.mkklcdnv6.com/19/v/1-1583464475.jpg",
                        mangaUrl = "https://manganelo.com/manga/hyer5231574354229"
                ),
                MangaInfo(
                        id = "pn918005",
                        name = "Solo Leveling",
                        imageUrl = "https://avt.mkklcdnv6.com/30/a/17-1583496340.jpg",
                        mangaUrl = "https://manganelo.com/manga/pn918005"
                ),
                MangaInfo(
                        id = "tomochan_wa_onnanoko",
                        name = "Tomo-chan wa Onnanoko!",
                        imageUrl = "https://avt.mkklcdnv6.com/19/e/1-1583464448.jpg",
                        mangaUrl = "https://manganelo.com/manga/tomochan_wa_onnanoko"
                )
        )

        assertEquals(expected, result)
    }

    @Test
    fun getLatest() {
    }

    @Test
    fun getFavorites() {
    }

    @Test
    fun searchMangaRequest() {
    }

    @Test
    fun extractChapterList() {
    }

    @Test
    fun extractImagesUrl() {
    }
}