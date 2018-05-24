package com.goosvandenbekerom.roulette.monitor.controllers

import com.goosvandenbekerom.roulette.monitor.domain.ErrorEntry
import com.goosvandenbekerom.roulette.monitor.repositories.ErrorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ErrorController {
    @Autowired private lateinit var repo: ErrorRepository

    @GetMapping("/api/greeting")
    fun all(): Iterable<ErrorEntry> = repo.findAll()
}