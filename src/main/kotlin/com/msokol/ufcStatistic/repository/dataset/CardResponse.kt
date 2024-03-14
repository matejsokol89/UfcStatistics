package com.msokol.ufcStatistic.repository.dataset

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

data class CardResponse(
    val event: EventDetails,
    val season: Season,
    val venue: Venue,
    val cards: Cards
)

data class EventDetails(
    val id: String,
    val uid: String,
    val date: String,
    val name: String,
    val shortName: String
)

data class Season(
    val year: Int,
    val displayName: String,
    val startDate: String,
    val endDate: String,
    val type: SeasonType
)

data class SeasonType(
    val id: String,
    val type: Int,
    val name: String,
    val startDate: String,
    val endDate: String,
    val week: Any // Assuming week can be any type, adjust as needed
)

data class Venue(
    val id: String,
    val fullName: String,
    val displayNameLocation: String,
    val address: Address,
    val grass: Boolean,
    val indoor: Boolean
)

data class Address(
    val city: String,
    val state: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val country: String?
)

data class Cards(
    val main: CardDetails
)

data class CardDetails(
    val name: String,
    val displayName: String,
    val cardSegment: String,
    val competitions: List<Competition>
)

data class Competition(
    val id: String,
    val uid: String,
    val date: String,
    val type: Type,
    //val types: List<Type>,
    val recent: Boolean,
    val status: Status,
    val cardSegment: CardSegment,
    val matchNumber: Int,
    val note: String,
    val competitors: List<Competitor>
)

data class Type(
    val id: String,
    val text: String,
    val abbreviation: String
)

data class Status(
    val clock: String,
    val displayClock: String,
    val period: Int,
    //val type: Type,
    val result: Result,
    val featured: Boolean
)

data class Result(
    val id: String,
    val name: String,
    val displayName: String,
    val shortDisplayName: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val description: String?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val displayDescription: String?,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val target: Target?
)

data class Target(
    val id: String,
    val name: String,
    val description: String,
    val displayDescription: String
)

data class CardSegment(
    val id: String,
    val name: String,
    val description: String
)

data class Competitor(
    val id: String,
    val uid: String,
    val type: String,
    val athlete: Athlete,
    val order: Int,
    val winner: Boolean,
    val displayRecord: String,
    val stats: List<Stat>
)

data class Athlete(
    val id: String,
    val uid: String,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val fullName: String,
    val shortName: String,
    val displayHeight: String,
    val displayWeight: String,
    val headshot: Image,
    val flag: Image,
    val age: Int,
    val gender: String,
    val displayReach: String,
    val stance: Stance,
    val weightClass: WeightClass,
    val images: List<Image>,
   // val accolades: List<Accolade>,
    val link: Link,
    val color: String,
    val alternateColor: String
)
data class Stance(
    val id: String,
    val text: String
)

data class WeightClass(
    val id: String,
    val text: String,
    val shortName: String,
    val slug: String
)

data class Image(
    val href: String,
  //  val alt: String
)

//data class Accolade(
//    val id: String,
//    val name: String,
//    val type: String
//)

data class Link(
    val language: String,
    val rel: List<String>,
    val href: String,
    val text: String,
    val shortText: String,
    val isExternal: Boolean,
    val isPremium: Boolean
)

data class Stat(
    val name: String,
    val displayName: String,
    //val shortDisplayName: String,
    val description: String,
    val abbreviation: String,
    val value: Double,
    val displayValue: String
)