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
        channel.queueDeclare("reader1", false, false, false, null);
        channel.queueDeclare("reader2", false, false, false, null);
        channel.queueDeclare("editor", false, false, false, null);
        channel.exchangeDeclare("exchange", "direct");
        channel.queueBind("reader1","exchange", "reader");
        channel.queueBind("reader2","exchange", "reader");
        channel.queueBind("editor","exchange", "editor");
    }

    public void consume(String str, EventHandler handler) throws IOException {
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
