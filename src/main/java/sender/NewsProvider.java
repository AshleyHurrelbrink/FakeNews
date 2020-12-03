package sender;

import model.Event;
import model.EventType;
import model.News;

import java.util.ArrayList;


public class NewsProvider {
    public static void main(String[] args) {
        News news1 = new News(1,"very true news pisici", "zoo", "editor", 2);
        News news2 = new News(2,"very true news catei", "zoo", "editor", 1);
        News news3 = new News(3,"soc soc si groaza", "misc", "editor", 2);

        ArrayList<Event> events = new ArrayList<Event>(10);

        events.add(new Event(news1, EventType.ADD));
        events.add(new Event(news2, EventType.ADD));
        events.add(new Event(news3, EventType.ADD));
        events.add(new Event(news3, EventType.READ));
        events.add(new Event(news3, EventType.READ));
        events.add(new Event(new News(2,"very true news catelusi", "misc", "editor", 1), EventType.UPDATE));
        events.add(new Event(news1, EventType.DELETE));
        events.add(new Event(news3, EventType.READ));

        NewsProviderManager newsProviderManager= NewsProviderManager.getInstance();

        newsProviderManager.publishAllNews(events);
    }
}
