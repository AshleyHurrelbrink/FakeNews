package handler;

import model.Event;
import service.NewsConsumerService;

public class ReaderEventHandler implements EventHandler{
    private NewsConsumerService newsConsumerService;
    private String domainInterest;

    public ReaderEventHandler(String domainInterest) {
        this.domainInterest = domainInterest;
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getEventType()) {
            case ADD:
                if(domainInterest.equals(event.getNews().getDomain())) {
//                    this.news.add(e.getNews());
                    System.out.println("News with topic you are interested in has been added '" + event.getNews().getTitle() + "'");
                }
                break;
            case UPDATE:
                if(domainInterest.equals(event.getNews().getDomain())) {
//                    this.news.add(e.getNews());
                    System.out.println("News just changed its topic to one you might be interested in and has been added to your list '" + event.getNews().getTitle() + "'");

                }
//                else {
//                    while (iterator.hasNext()) {
//                        if(iterator.next().getId() == e.getNews().getId()) {
//                            iterator.remove();
//                            System.out.println("News just changed its topic to one you are not interested in and has been removed from your list '" + e.getNews().getTitle() + "'");
//                        }
//                    }
//                }
                break;
            case DELETE:
                if(domainInterest.equals(event.getNews().getDomain())) {
//                    news.remove(e.getNews());
                    System.out.println("A news you are interested in has been deleted '" + event.getNews().getTitle() + "'");
                }
                break;
        }
    }
}
