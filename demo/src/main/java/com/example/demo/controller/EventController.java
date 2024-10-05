package com.example.demo.controller;

import com.example.demo.authentication.user.AuthenticatedUser;
import com.example.demo.dto.request.event.EventRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.event.EventResponseData;
import com.example.demo.dto.response.event.EventResponseDataList;
import com.example.demo.entity.User;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ResponseDto<EventResponseDataList>> getEvents(@AuthenticatedUser User user) {
        EventResponseDataList eventResponseDataList = this.eventService.getEvents(user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", eventResponseDataList), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDto<Void>> createEvent(@AuthenticatedUser User user, @Valid @RequestBody EventRequestDto eventDto) {
        this.eventService.createEvent(user, eventDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK"), HttpStatus.OK);
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<ResponseDto<EventResponseData>> updateEvents(@AuthenticatedUser User user, @Valid @RequestBody EventRequestDto eventRequestDto, @PathVariable UUID eventId) {
        EventResponseData eventResponseData = this.eventService.updateEvent(user, eventRequestDto, eventId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "OK", eventResponseData), HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ResponseDto<Void>> deleteEvent(@PathVariable UUID eventId, @AuthenticatedUser User user) {
        this.eventService.deleteEvent(eventId, user);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "deleted"), HttpStatus.OK);

    }
}
