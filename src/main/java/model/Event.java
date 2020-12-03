package model;

import java.io.Serializable;

public class Event implements Serializable {
    private News news;
    private EventType eventType;

    public Event(News news, EventType eventType) {
        this.news = news;
        this.eventType = eventType;
    }

    public News getNews() {
        return news;
    }

    public EventType getEventType() {
        return eventType;
    }
}
