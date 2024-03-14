package com.msokol.ufcStatistic.repository.dataset

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.persistence.Entity

//@Entity


data class Event(
    val id: String,
    val uid: String,
    val date: String,
    val name: String,
    val shortName: String
)

data class EventResponse(
    val event: Event
)