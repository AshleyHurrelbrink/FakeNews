package handler;

import model.Event;
import model.EventType;
import service.NewsConsumerService;

import java.util.concurrent.CopyOnWriteArrayList;


public class EditorEventHandler implements EventHandler{
    private NewsConsumerService newsConsumerService;
    private CopyOnWriteArrayList<Integer> readersList = new CopyOnWriteArrayList<Integer>();

    @Override
    public void handleEvent(Event event) {
        if(event.getEventType().equals(EventType.READ)) {
            System.out.println("this is a read event");
        }
    }
}
