package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.Event;
import model.EventType;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;

public class NewsProviderManager {
    private static NewsProviderManager newsProviderManager_instance =null;
    private ConnectionFactory connectionFactory;
    private Channel channel;
    private Connection connection;

    private NewsProviderManager() {
        connectionFactory = new ConnectionFactory();

    }

    public static NewsProviderManager getInstance() {
        if (newsProviderManager_instance == null)
        {
            newsProviderManager_instance = new NewsProviderManager();
        }
        return newsProviderManager_instance;
    }

    private void publishNews (Event event) throws IOException {
        byte[] message = SerializationUtils.serialize(event);
        if ((event.getEventType() == EventType.ADD)) {
            channel.basicPublish("exchange", event.getNews().getDomain(), null, message);
        } else if ((event.getEventType() == EventType.UPDATE)) {
            channel.basicPublish("exchange", "reader", null, message);
        } else if ((event.getEventType() == EventType.DELETE)) {
            channel.basicPublish("exchange", event.getNews().getDomain(), null, message);
        } else if ((event.getEventType() == EventType.READ)) {
            channel.basicPublish("exchange", "editor", null, message);
        }
    }

    public void publishAllNews(ArrayList<Event> events){
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("exchange", "direct");
            for (Event elem : events){
                this.publishNews(elem);
                System.out.println("NewsProvider, provided an event");
            }
        }catch (Exception e){
            System.out.println("Found exception");
        }
    }
}
