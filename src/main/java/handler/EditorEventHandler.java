package handler;

import model.Event;
import model.EventType;


public class EditorEventHandler implements EventHandler{

    @Override
    public void handleEvent(Event event) {
        if(event.getEventType().equals(EventType.READ)) {
            System.out.println("this is a read event");
        }
    }
}
