package consumer;

import handler.EditorEventHandler;
import handler.EventHandler;
import handler.ReaderEventHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewsConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ReaderEventHandler eventHandler1 = new ReaderEventHandler("zoo");
        EventHandler eventHandler2 = new ReaderEventHandler("misc");
        EventHandler eventHandler3 = new EditorEventHandler();

        NewsConsumerManager newsConsumerManager = NewsConsumerManager.getInstance();

        newsConsumerManager.consume("reader1", eventHandler1);
        newsConsumerManager.consume("reader2", eventHandler2);
        newsConsumerManager.consume("editor", eventHandler3);
    }
}
