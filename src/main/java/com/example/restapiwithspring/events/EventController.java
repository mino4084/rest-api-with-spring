package com.example.restapiwithspring.events;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * packageName    : com.example.restapiwithspring.events
 * fileName       : EventController
 * author         : user
 * date           : 2024-02-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-02-27        user       최초 생성
 */
@RestController
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @PostMapping
    public ResponseEntity createEvent(@RequestBody Event event) {
        Event save = this.eventRepository.save(event);

        URI createdUri = linkTo(EventController.class).slash(event.getID()).toUri();
        event.setID(10);
        return ResponseEntity.created(createdUri).body(event);
    }

}
