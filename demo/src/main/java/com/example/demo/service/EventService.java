package com.example.demo.service;

import com.example.demo.dto.request.event.EventRequestDto;
import com.example.demo.dto.response.event.EventResponseData;
import com.example.demo.dto.response.event.EventResponseDataList;
import com.example.demo.entity.Event;
import com.example.demo.entity.Family;
import com.example.demo.entity.Users;
import com.example.demo.exception.ForbiddenException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.exception.error.ErrorCode;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.FamilyRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final FamilyRepository familyRepository;

    public EventResponseDataList getEvents(Users user) {
        Family family = user.getFamily();
        List<Event> events = family.getEvents();
        List<EventResponseData> eventResponseDataList = events.stream()
                .map(event -> new EventResponseData(event.getContent(), event.getDate().toString(), event.getId()))
                .collect(Collectors.toList());

        EventResponseDataList response = EventResponseDataList.of(eventResponseDataList);

        return response;
    }
    public Map<String, List<EventResponseData>> getEventsByDate(Users user) {
        Family family = user.getFamily();
        List<Event> events = family.getEvents();

        Map<String, List<EventResponseData>> eventsByDate = events.stream()
                .map(event -> new EventResponseData(event.getContent(), event.getDate().toString(), event.getId()))
                .collect(Collectors.groupingBy(EventResponseData::getDate));

        return eventsByDate;
    }

    public void createEvent(Users user, EventRequestDto eventDto) {
        Event event = Event.builder()
                .date(LocalDate.parse(eventDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .content(eventDto.getContent())
                .family(user.getFamily())
                .build();

        this.eventRepository.save(event);
    }

    public EventResponseData updateEvent(Users user, EventRequestDto eventDto, UUID eventId) {
        Event newEvent = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.EVENT_NOT_FOUND));
        validateEvent(newEvent, user);

        log.info(eventDto.getDate());
        log.info(eventDto.getContent());

        newEvent.setContent(eventDto.getContent());
        newEvent.setDate(LocalDate.parse(eventDto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        this.eventRepository.save(newEvent);
        EventResponseData eventResponseData = EventResponseData.of(newEvent);
        return eventResponseData;
    }

    public void deleteEvent(UUID eventId, Users user) {
        Event oldEvent = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new ForbiddenException(ErrorCode.NO_ACCESS));
        validateEvent(oldEvent, user);
        this.eventRepository.delete(oldEvent);
    }

    private void validateEvent(Event event, Users user) {
        if(!event.getFamily().getId().equals(user.getFamily().getId())){
            log.info(event.getFamily().getId().toString());
            log.info(user.getFamily().getId().toString());
            throw new ForbiddenException(ErrorCode.NO_ACCESS);
        }
    }
}
