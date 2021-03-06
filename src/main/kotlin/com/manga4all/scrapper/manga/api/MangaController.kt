package com.manga4all.scrapper.manga.api

import com.manga4all.scrapper.SearchMangaRequest
import com.manga4all.scrapper.manga.MangaChapter
import com.manga4all.scrapper.manga.MangaInfo
import com.manga4all.scrapper.manga.sources.MangaSourceExtractor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api/v1/scrapper")
class MangaController {
    @GetMapping("/search/{name}/{page}")
    fun search(@PathVariable("name") name: String,
               @PathVariable("page") page: Int): ResponseEntity<List<MangaInfo>> {
        return ResponseEntity.ok(MangaSourceExtractor.getRandomSource().searchMangaRequest(SearchMangaRequest(name, page)))
    }

    @GetMapping("/popular/{page}")
    fun popular(@PathVariable("page") page: Int): ResponseEntity<List<MangaInfo>> {
        return ResponseEntity.ok(MangaSourceExtractor.getRandomSource().searchPopular(page))
    }

    @GetMapping("/latest/{page}")
    fun latest(@PathVariable("page") page: Int): ResponseEntity<List<MangaInfo>> {
        return ResponseEntity.ok(MangaSourceExtractor.getRandomSource().getLatest(page))
    }

    @PostMapping("/chapters")
    fun getMangaChapters(@RequestBody(required = true) mangaInfo: MangaInfo): ResponseEntity<List<MangaChapter>> {
        return ResponseEntity.ok(MangaSourceExtractor.getRandomSource().extractChapterList(mangaInfo))
    }

    @PostMapping("/extract")
    fun extractChapterInfo(@RequestBody(required = true) mangaChapter: MangaChapter): ResponseEntity<List<String>> {
        return ResponseEntity.ok(MangaSourceExtractor.getRandomSource().extractImagesUrl(mangaChapter))
    }
}
