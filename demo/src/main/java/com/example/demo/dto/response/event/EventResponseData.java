package com.example.demo.dto.response.event;

import com.example.demo.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class EventResponseData {
    private String content;
    private String date;
    private UUID eventId;
    public static EventResponseData of(Event event) {
        return new EventResponseData(
                event.getContent(),
                event.getDate().toString(),
                event.getId()
        );
    }
}
