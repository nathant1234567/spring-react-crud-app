package com.nathan.reacttut.react_tutorial.web;

import com.nathan.reacttut.react_tutorial.model.Event;
import com.nathan.reacttut.react_tutorial.model.EventRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    Collection<Event> events() {
        return eventRepository.findAll();
    }

    @GetMapping("/event/{id}")
    ResponseEntity<?> getEvent(@PathVariable Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/event")
    ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws URISyntaxException {
        Event result = eventRepository.save(event);
        return ResponseEntity.created(new URI("/api/event/" + result.getId())).body(result);
    }

    @PutMapping("/event/{id}")
    ResponseEntity<Event> updateEvent(@Valid @RequestBody Event event) {
        Event result = eventRepository.save(event);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/event/{id}")
    ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
