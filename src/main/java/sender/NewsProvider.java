package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.Event;
import model.EventType;
import model.News;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

//public class NewsProvider {
//    public static void main(String[] args) throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//
//        try(Connection connection = connectionFactory.newConnection()){
//            Channel channel = connection.createChannel();
//            channel.queueDeclare("editor", false, false, false, null);
//            channel.exchangeDeclare("exchange", "direct");
//            News news1 = new News(1,"very true news pisici", "zoo", "editor", 2);
//            News news2 = new News(2,"very true news catei", "zoo", "editor", 1);
//            News news3 = new News(3,"soc soc si groaza", "misc", "editor", 2);
//            Event event1 = new Event(news1, EventType.ADD);
//            Event event2 = new Event(news2, EventType.ADD);
//            Event event3 = new Event(news3, EventType.ADD);
//            Event event4 = new Event(news3, EventType.READ);
//            byte[] message = SerializationUtils.serialize(event1);
//            channel.basicPublish("exchange", "reader", null, message);
//            message = SerializationUtils.serialize(event2);
//            channel.basicPublish("exchange", "reader", null, message);
//            message = SerializationUtils.serialize(event3);
//            channel.basicPublish("exchange", "reader", null, message);
//            message = SerializationUtils.serialize(event4);
//            channel.basicPublish("exchange", "editor", null, message);
////            String readEvent = "a news has been read";
////            channel.basicPublish("", "editor", false, null, readEvent.getBytes());
//            System.out.println("NewsProvider, provided an event");
//        }
//    }
//}
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

        for (Event elem : events){
            newsProviderManager.publishNews(elem);
            System.out.println("NewsProvider, provided an event");
        }
    }
}
