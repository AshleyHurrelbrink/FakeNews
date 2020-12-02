package consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import handler.EditorEventHandler;
import handler.EventHandler;
import handler.ReaderEventHandler;
import model.Event;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewsConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        ReaderEventHandler eventHandler1 = new ReaderEventHandler("zoo");
        EventHandler eventHandler2 = new ReaderEventHandler("misc");
        EventHandler eventHandler3 = new EditorEventHandler();

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("reader1", false, false, false, null);
        channel.queueDeclare("reader2", false, false, false, null);
        channel.queueDeclare("editor", false, false, false, null);
        channel.exchangeDeclare("exchange", "direct");
        channel.queueBind("reader1","exchange", "reader");
        channel.queueBind("reader2","exchange", "reader");
        channel.queueBind("editor","exchange", "editor");

        channel.basicConsume("reader1", true, (consumerTag, message) -> {
            byte[] data = message.getBody();
            Event event = SerializationUtils.deserialize(data);
            System.out.println("Reader1 event: ");
//            System.out.println("Just recieved message: " + event.getNews());
            System.out.println("EventType " + event.getEventType());
            eventHandler1.handleEvent(event);
            System.out.println("PT handler1  " + eventHandler1.getNewsConsumerService().getNews());
        }, consumerTag -> {});

        channel.basicConsume("reader2", true, (consumerTag, message) -> {
            byte[] data = message.getBody();
            Event event = SerializationUtils.deserialize(data);
            System.out.println("Reader2 event: ");
//            System.out.println("Just recieved message: " + event.getNews());
            System.out.println("EventType " + event.getEventType());
            eventHandler2.handleEvent(event);
        }, consumerTag -> {});

        channel.basicConsume("editor", true, (consumerTag, message) -> {
            byte[] data = message.getBody();
            Event event = SerializationUtils.deserialize(data);
            System.out.println("Editor event");
            eventHandler3.handleEvent(event);
        }, consumerTag -> {});


    }
}
