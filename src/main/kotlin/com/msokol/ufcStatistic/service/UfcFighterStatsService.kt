package com.msokol.ufcStatistic.service

import com.msokol.ufcStatistic.repository.dataset.CardResponse
import com.msokol.ufcStatistic.repository.UfcFighterRepository
import com.msokol.ufcStatistic.repository.dataset.EventResponse
import com.msokol.ufcStatistic.repository.dataset.UfcFighterStats
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.net.URL

@Service
class UfcFighterStatsService(val ufcFighterRepository: UfcFighterRepository) {

    fun ufcStats(): MutableIterable<UfcFighterStats> {
        val res =
            JSONObject(URL("https://site.web.api.espn.com/apis/common/v3/sports/mma/ufc/fightcenter/600038652?region=us&lang=en&contentorigin=espn&showAirings").readText())

        val competitions = JSONObject(
            JSONObject(res.getString("cards"))
                .getString("main")
        ).getJSONArray("competitions")

        val list1 = mutableListOf<String?>()

        for (i in 0 until competitions.length()) {

            composeStats(competitions, i, 0)
            composeStats(competitions, i, 1)


//            val map = mutableMapOf(
//                "firstFighterFullName" to firstFighterFullName,
//                "firstFighterKnockDowns" to firstFighterKnockDowns,
//                "firstFighterTotalStrikes" to firstFighterTotalStrikes,
//                "firstFighterSigStrikes" to firstFighterSigStrikes,
//                "firstFighterHeadStrikes" to firstFighterHeadStrikes,
//                "firstFighterBodyStrikes" to firstFighterBodyStrikes,
//                "firstFighterTimeInControl" to firstFighterTimeInControl,
//                "firstFighterTakeDowns" to firstFighterTakeDowns,
//                "firstFighterSubmissions" to firstFighterSubmissions,
//
//                "secondFighterFullName" to secondFighterFullName,
//                "secondFighterKnockDowns" to secondFighterKnockDowns,
//                "secondFighterTotalStrikes" to secondFighterTotalStrikes,
//                "secondFighterSigStrikes" to secondFighterSigStrikes,
//                "secondFighterHeadStrikes" to secondFighterHeadStrikes,
//                "secondFighterBodyStrikes" to secondFighterBodyStrikes,
//                "secondFighterTimeInControl" to secondFighterTimeInControl,
//                "secondFighterTakeDowns" to secondFighterTakeDowns,
//                "secondFighterSubmissions" to secondFighterSubmissions
//
//            )
//
//            list1.add(map.toString())
        }

        return ufcFighterRepository.findAll()
    }

    fun composeStats(competitions: JSONArray, i: Int, fighterInList: Int) {
        val fighterId =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getString("id").toInt()
        val fighterFullName = JSONObject(
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getString("athlete")
        ).getString("displayName")
        val fighterKnockDowns =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(0).getString("displayValue")
        val fighterTotalStrikes =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(1).getString("displayValue")
        val fighterSigStrikes =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(2).getString("displayValue")
        val fighterHeadStrikes =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(3).getString("displayValue")
        val fighterBodyStrikes =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(4).getString("displayValue")
        val fighterTimeInControl =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(5).getString("displayValue")
        val fighterTakeDowns =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(6).getString("displayValue")
        val fighterSubmissions =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getJSONArray("stats").getJSONObject(7).getString("displayValue")
        val fighterWinOrLose =
            JSONObject(competitions.get(i).toString()).getJSONArray("competitors").getJSONObject(fighterInList)
                .getString("winner").toBoolean()
        val ufcFighterStats = UfcFighterStats(
            fighterId = fighterId,
            fighterFullName = fighterFullName,
            fighterTotalStrikes = fighterTotalStrikes,
            fighterSigStrikes = fighterSigStrikes,
            fighterHeadStrikes = fighterHeadStrikes,
            fighterBodyStrikes = fighterBodyStrikes,
            fighterTimeInControl = fighterTimeInControl,
            firstFighterTakeDowns = fighterTakeDowns,
            fighterSubmissions = fighterSubmissions,
            fighterWinOrLose = fighterWinOrLose
        )
        println("this is obj $ufcFighterStats")
        ufcFighterRepository.save(ufcFighterStats)
    }
    fun getEvent(): EventResponse? {
        val restTemplate = RestTemplate()
        return restTemplate.getForObject("https://site.web.api.espn.com/apis/common/v3/sports/mma/ufc/fightcenter/600038652", EventResponse::class.java)
    }

    fun getCard(): CardResponse? {
        val restTemplate = RestTemplate()
        return restTemplate.getForObject("https://site.web.api.espn.com/apis/common/v3/sports/mma/ufc/fightcenter/600038652", CardResponse::class.java)
    }

}