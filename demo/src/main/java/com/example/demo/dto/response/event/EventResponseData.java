package com.example.demo.dto.response.event;

import com.example.demo.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EventResponseData {
    private String content;
    private String date;
    private String place;

    public static EventResponseData of(Event event) {
        return new EventResponseData(
                event.getContent(),
                event.getDate().toString(),
                event.getPlace()
        );
    }
}
