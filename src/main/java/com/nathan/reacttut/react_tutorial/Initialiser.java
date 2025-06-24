package com.nathan.reacttut.react_tutorial;

import com.nathan.reacttut.react_tutorial.model.Event;
import com.nathan.reacttut.react_tutorial.model.Group;
import com.nathan.reacttut.react_tutorial.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Component
class Initialiser implements CommandLineRunner {
    private final GroupRepository repository;

    public Initialiser(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Seattle JUG", "Denver JUG", "Dublin JUG", "London JUG")
                .forEach(name -> repository.save(new Group(name)));

        Group djug = repository.findByName("Seattle JUG");
        Event e = Event.builder().title("micro Frontends for Java Developers")
                .description("micro Frontends for Java Developers")
                .date(Instant.parse("2022-09-13T17:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
