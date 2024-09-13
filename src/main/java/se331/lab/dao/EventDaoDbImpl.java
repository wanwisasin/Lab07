package se331.lab.dao;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import se331.lab.repository.EventRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile("db")

public class EventDaoDbImpl implements EventDao {
    final EventRepository eventRepository;
    @Override
    public Integer getEventSize() {
        return Math.toIntExact(eventRepository.count());
    }

    @Override
    public List<Event> getEvents(Integer pageSize, Integer page) {
        List<Event> events = eventRepository.findAll();
        pageSize = pageSize == null ? events.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        List<Event> output = events.subList(firstIndex, firstIndex + pageSize);
        return output;
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}

