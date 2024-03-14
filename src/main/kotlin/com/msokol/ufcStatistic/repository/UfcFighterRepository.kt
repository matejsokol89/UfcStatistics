package com.msokol.ufcStatistic.repository

import com.msokol.ufcStatistic.repository.dataset.UfcFighterStats
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UfcFighterRepository : CrudRepository<UfcFighterStats, Long> {
}