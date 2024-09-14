package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Event;

import java.util.List;


public interface EventService {
    Integer getEventSize();
    Page<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
}
