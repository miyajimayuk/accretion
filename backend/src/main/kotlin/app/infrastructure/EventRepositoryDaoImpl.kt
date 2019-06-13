package app.infrastructure

import app.domain.model.Event
import app.domain.repository.EventRepository
import app.infrastructure.doma.dao.EventEntityDao
import app.infrastructure.doma.entity.EventEntity
import org.springframework.stereotype.Repository

@Repository
class EventRepositoryDaoImpl(
    private val eventEntityDao: EventEntityDao
) : EventRepository {
    override fun findAll(): List<Event> {
        return eventEntityDao.selectAll().map { _MapToModel(it) }
    }

    private fun _MapToModel(eventEntity: EventEntity) =
        Event(
            id = eventEntity.id,
            title = eventEntity.title,
            content = eventEntity.content
        )
}