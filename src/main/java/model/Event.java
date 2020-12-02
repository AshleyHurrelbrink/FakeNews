package model;

import java.io.Serializable;
import java.util.UUID;

public class Event implements Serializable {
    private String id; //= UUID.randomUUID().toString();
    private News news;
    private EventType eventType;

    public Event(News news, EventType eventType) {
        this.news = news;
        this.eventType = eventType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public EventType getEventType() {
        return eventType;
    }
}
