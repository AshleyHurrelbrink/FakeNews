package sender;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.Event;
import model.EventType;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import static model.EventType.ADD;

public class NewsProviderManager {
    private static NewsProviderManager newsProviderManager_instance =null;
    private ConnectionFactory connectionFactory;
    private Channel channel;
    private Connection connection;

    private NewsProviderManager() {
        connectionFactory = new ConnectionFactory();
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare("editor", false, false, false, null);
            channel.exchangeDeclare("exchange", "direct");
        }catch (Exception e){
            System.out.println("Found exception");
        }

    }

    public static NewsProviderManager getInstance() {
        if (newsProviderManager_instance == null)
        {
            newsProviderManager_instance = new NewsProviderManager();
        }
        return newsProviderManager_instance;
    }

    public void publishNews (Event event) throws IOException, TimeoutException {
        byte[] message = SerializationUtils.serialize(event);
        if(!(event.getEventType() == EventType.ADD)) {
            channel.basicPublish("exchange", "reader", null, message);

        }else if(!(event.getEventType() == EventType.READ)){
            channel.basicPublish("exchange", "editor", null, message);
        }
    }
}
