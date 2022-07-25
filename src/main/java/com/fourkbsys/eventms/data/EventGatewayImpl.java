package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.data.mapper.EventMapper;
import com.fourkbsys.eventms.data.mapper.RoomMapper;
import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Event.EventGateway;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class EventGatewayImpl implements EventGateway {

    private final EventPostgresqlRepository eventRepository;

    public EventGatewayImpl(EventPostgresqlRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> findEventsWithCriteria(String criteria) {

        Optional<Specification<EventEntity>> spec = Optional.of(EventEntityCriteriaFilter.filterEvent(criteria));
        if (!spec.isPresent()){
            throw new IllegalArgumentException("Invalid date format");
        }
        return eventRepository.findAll(spec.get()).stream().map(EventMapper::toModel).toList();
    }

    @Override
    public List<Event> findAllEventsFromDate(LocalDateTime dateTime) {
        return eventRepository.retrieveAllByDate(dateTime).stream().map(EventMapper::toModel).toList();
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id).map(EventMapper::toModel);
    }

    @Override
    public Event saveEvent(Event event) {
        return Stream.of(event)
                .map(EventMapper::toEntity)
                .map(eventRepository::save)
                .map(EventMapper::toModel)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error mapping"));
    }

    @Override
    public boolean isOverlapping(Event event) {
        RoomEntity roomEntity = RoomMapper.toEntity(event.getRoom());
        Long count = eventRepository.countEventOverlappingRoomId(event.getFromDate(), event.getToDate(), roomEntity);
        return count > 0;
    }

}
