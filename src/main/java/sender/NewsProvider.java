package sender;

import model.Event;
import model.EventType;
import model.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;


public class NewsProvider {
    public static void main(String[] args) throws IOException, TimeoutException {
        News news1 = new News(1,"very true news pisici", "zoo", "editor", 2);
        News news2 = new News(2,"very true news catei", "zoo", "editor", 1);
        News news3 = new News(3,"soc soc si groaza", "misc", "editor", 2);

        ArrayList<Event> events = new ArrayList<Event>(10);

        events.add(new Event(news1, EventType.ADD));
        events.add(new Event(news2, EventType.ADD));
        events.add(new Event(news3, EventType.ADD));
        events.add(new Event(news3, EventType.READ));

        NewsProviderManager newsProviderManager= NewsProviderManager.getInstance();

        newsProviderManager.publishAllNews(events);
    }
}
