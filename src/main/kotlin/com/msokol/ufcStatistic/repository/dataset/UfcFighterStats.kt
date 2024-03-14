package com.msokol.ufcStatistic.repository.dataset

import jakarta.persistence.*

@Entity
data class UfcFighterStats(

    @Id
    val fighterId: Int?,
    val fighterFullName: String,
    val fighterTotalStrikes: String,
    val fighterSigStrikes: String,
    val fighterHeadStrikes: String,
    val fighterBodyStrikes: String,
    val fighterTimeInControl: String,
    val firstFighterTakeDowns: String,
    val fighterSubmissions: String,
    val fighterWinOrLose: Boolean
)


