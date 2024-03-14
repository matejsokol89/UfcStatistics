package com.msokol.ufcStatistic.controller

import com.msokol.ufcStatistic.repository.dataset.CardResponse
import com.msokol.ufcStatistic.repository.dataset.EventResponse
import com.msokol.ufcStatistic.repository.dataset.UfcFighterStats
import com.msokol.ufcStatistic.service.UfcFighterStatsService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.ui.Model

//@RestController
@Controller
class UfcFighterStatsController(val ufcFighterStatsService: UfcFighterStatsService) {

    @GetMapping("/ufc-stats")
    fun  ufcStats(): MutableIterable<UfcFighterStats> = ufcFighterStatsService.ufcStats()

    @GetMapping("/event")
    fun getEvent(): EventResponse? {
        return ufcFighterStatsService.getEvent()
    }

    @GetMapping("/card")
    fun getCard(): CardResponse? {
        return ufcFighterStatsService.getCard()
    }

    @GetMapping("/cardResponse")
    fun showCardPage(model: Model): String {
        val cardResponse = ufcFighterStatsService.getCard()
        model.addAttribute("cardResponse", cardResponse)
        println(model)
        return "cardPage"
    }

    @GetMapping("/cardResponseGrid")
    fun showCardPageGrid(model: Model): String {
        val cardResponse = ufcFighterStatsService.getCard()
        model.addAttribute("cardResponse", cardResponse)
        println(model)
        return "cardPageGrid"
    }
}