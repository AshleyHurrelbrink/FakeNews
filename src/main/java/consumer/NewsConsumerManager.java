package consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import handler.EventHandler;
import handler.ReaderEventHandler;
import model.Event;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewsConsumerManager {
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;
    private static NewsConsumerManager newsConsumerManager_instance=null;

    private NewsConsumerManager() throws IOException, TimeoutException {
        connectionFactory = new ConnectionFactory();
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        this.init();
    }

    public static NewsConsumerManager getInstance() throws IOException, TimeoutException {
        if (newsConsumerManager_instance == null)
        {
            newsConsumerManager_instance = new NewsConsumerManager();
        }
        return newsConsumerManager_instance;
    }

    public void init() throws IOException {
        channel.exchangeDeclare("exchange", "direct");
    }

    public void consume(String str, EventHandler handler, String domain) throws IOException {
        channel.queueDeclare(str, false, false, false, null);
        channel.queueBind(str,"exchange", domain);
        channel.basicConsume(str, true, (consumerTag, message) -> {
            byte[] data = message.getBody();
            Event event = SerializationUtils.deserialize(data);
            System.out.println(str + " event: ");
            System.out.println("---> EventType " + event.getEventType());
            handler.handleEvent(event);
            if(handler instanceof ReaderEventHandler){
                System.out.println("---> " + "  " + ((ReaderEventHandler) handler).getNewsConsumerService().getNews());
            }

        }, consumerTag -> {});
    }

}
