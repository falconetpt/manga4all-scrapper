package com.manga4all.scrapper.manga.sources.manganelo

import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.utils.http.connector.MockHttpConnector
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
        val html = readHtmlFile("src/test/resources/manga.sources.manganelo/latest.html")
        val httpConnector = MockHttpConnector(html)
        val result = MangaNeloScrapper(httpConnector).getLatest(1)

        val expected = listOf(
                MangaInfo(
                        id = "gd919622",
                        name = "Battle Through The Heavens: Return Of The Beasts",
                        imageUrl = "https://avt.mkklcdnv6.com/38/u/18-1583498599.jpg",
                        mangaUrl = "https://manganelo.com/manga/gd919622"
                ),
                MangaInfo(
                        id = "jichou_shinai_motoyuusha_no_tsuyokute_tanoshii_new_game",
                        name = "Jichou shinai Motoyuusha no Tsuyokute Tanoshii New Game",
                        imageUrl = "https://avt.mkklcdnv6.com/37/e/16-1583494801.jpg",
                        mangaUrl = "https://manganelo.com/manga/jichou_shinai_motoyuusha_no_tsuyokute_tanoshii_new_game"
                )
        )

        assertEquals(expected, result)
    }

    @Test
    fun getFavorites() {
    }

    @Test
    fun searchMangaRequest() {
        val html = readHtmlFile("src/test/resources/manga.sources.manganelo/search.html")
        val httpConnector = MockHttpConnector(html)
        val query = SearchMangaRequest(query = "kimetsu", page = 1)
        val result = MangaNeloScrapper(httpConnector).searchMangaRequest(query)

        val expected = listOf(
                MangaInfo(
                        id = "kimetsu_no_yaiba",
                        name = "Kimetsu no Yaiba",
                        imageUrl = "https://avt.mkklcdnv6.com/30/e/13-1583488820.jpg",
                        mangaUrl = "https://manganelo.com/manga/kimetsu_no_yaiba"
                ),
                MangaInfo(
                        id = "ww918980",
                        name = "Kimetsu no Aima!",
                        imageUrl = "https://avt.mkklcdnv6.com/15/e/18-1583497671.jpg",
                        mangaUrl = "https://manganelo.com/manga/ww918980"
                ),
                MangaInfo(
                        id = "ou918986",
                        name = "Kimetsu no Yaiba: Tomioka Giyuu Gaiden",
                        imageUrl = "https://avt.mkklcdnv6.com/15/k/18-1583497684.jpg",
                        mangaUrl = "https://manganelo.com/manga/ou918986"
                ),
                MangaInfo(
                        id = "enma_no_hanayome_to_kimetsukerareta_fukou_na_ore_no_jinsei_keikaku",
                        name = "Enma no Hanayome to Kimetsukerareta Fukou na Ore no Jinsei Keikaku",
                        imageUrl = "https://avt.mkklcdnv6.com/49/o/3-1583469627.jpg",
                        mangaUrl = "https://manganelo.com/manga/enma_no_hanayome_to_kimetsukerareta_fukou_na_ore_no_jinsei_keikaku"
                )
        )

        assertEquals(expected, result)
    }

    @Test
    fun extractChapterList() {
        val html = readHtmlFile("src/test/resources/manga.sources.manganelo/chapters.html")
        val httpConnector = MockHttpConnector(html)
        val mangaInfo = MangaInfo(
                id = "kimetsu_no_yaiba",
                name = "Kimetsu no yaiba",
                imageUrl = "",
                mangaUrl = ""
        )
        val result = MangaNeloScrapper(httpConnector).extractChapterList(mangaInfo)

        val expected = listOf(
                MangaChapter(
                        number = "10",
                        name = "Kimetsu no Yaiba Chapter 10 : Swamp Of Abduction",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "9",
                        name = "Kimetsu no Yaiba Chapter 9 : Welcome Home",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "8",
                        name = "Kimetsu no Yaiba Chapter 8 : Big Brother",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "7.5",
                        name = "Kimetsu no Yaiba Chapter 7.5 : Omake",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "7",
                        name = "Kimetsu no Yaiba Chapter 7 : Spirits Of The Dead",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "6",
                        name = "Kimetsu no Yaiba Chapter 6 : Pile O' Hands",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "5",
                        name = "Kimetsu no Yaiba Chapter 5 : Tanjirou Diary - Part Two",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "4",
                        name = "Kimetsu no Yaiba Chapter 4 : Tanjirou Diary - Part One",
                        mangaId = "kimetsu_no_yaiba"

                ),
                MangaChapter(
                        number = "3",
                        name = "Kimetsu no Yaiba Chapter 3 : I Will Return Before Dawn",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "2",
                        name = "Kimetsu no Yaiba Chapter 2 : A Person Unknown",
                        mangaId = "kimetsu_no_yaiba"
                ),
                MangaChapter(
                        number = "1",
                        name = "Kimetsu no Yaiba Chapter 1 : Cruelty",
                        mangaId = "kimetsu_no_yaiba"
                )
        )

        assertEquals(expected, result)
    }

    @Test
    fun extractImagesUrl() {
        val html = readHtmlFile("src/test/resources/manga.sources.manganelo/chapterImages.html")
        val httpConnector = MockHttpConnector(html)
        val result = MangaNeloScrapper(httpConnector)
                .extractImagesUrl(
                        MangaChapter(
                                name = "kimetsu_no_yaiba",
                                number = "1",
                                mangaId = "kimetsu_no_yaiba"
                        )
                )

        val expected = (1..55)
                .map { "https://s6.mkklcdnv6.com/mangakakalot/k1/kimetsu_no_yaiba/chapter_1_cruelty/$it.jpg" }

        assertEquals(expected, result)
    }
}