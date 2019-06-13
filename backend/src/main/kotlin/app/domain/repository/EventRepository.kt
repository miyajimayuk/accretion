package app.domain.repository

import app.domain.model.Event

interface EventRepository {
    fun findAll(): List<Event>
}