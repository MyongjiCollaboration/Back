package com.example.demo.dto.response.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
public class EventResponseDataList {
    private List<EventResponseData> eventReponseDataList;
    public static EventResponseDataList of(List<EventResponseData> eventResponseDataList) {
        // null 체크 추가
        if (eventResponseDataList == null) {
            eventResponseDataList = List.of(); // 빈 리스트로 초기화
        }
        return new EventResponseDataList(eventResponseDataList);
    }
}
