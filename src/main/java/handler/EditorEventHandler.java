package handler;

import model.Event;
import model.EventType;
import service.NewsConsumerService;

public class EditorEventHandler implements EventHandler{
    private NewsConsumerService newsConsumerService;

    @Override
    public void handleEvent(Event event) {
        if(event.getEventType().equals(EventType.READ)) {
            System.out.println("this is a read event");
        }
    }
}
