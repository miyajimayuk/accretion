package app.application

import app.domain.repository.EventRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class EventController(
    private val eventRepository: EventRepository
) {
    @GetMapping
    fun getModel() =
            eventRepository.findAll()
}