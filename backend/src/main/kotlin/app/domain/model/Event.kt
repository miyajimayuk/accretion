package app.domain.model

import app.domain.fundamental.EventContent
import app.domain.fundamental.EventId
import app.domain.fundamental.EventTitle

data class Event(
    val id: EventId,
    val title: EventTitle,
    val content: EventContent
)