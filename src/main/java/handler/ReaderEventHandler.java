package handler;

import model.Event;
import service.NewsConsumerService;

public class ReaderEventHandler implements EventHandler{
    private NewsConsumerService newsConsumerService = new NewsConsumerService();
    private String domainInterest;

    public ReaderEventHandler(String domainInterest) {
        this.domainInterest = domainInterest;
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getEventType()) {
            case ADD:
                if(domainInterest.equals(event.getNews().getDomain())) {
                    newsConsumerService.addNews(event.getNews());
                    System.out.println("News with topic you are interested in has been added '" + event.getNews().getTitle() + "'");
                }
                break;
            case UPDATE:
                if(domainInterest.equals(event.getNews().getDomain())) {
                    newsConsumerService.updateNewsIfDomainInterest(event.getNews());
                    System.out.println("News just changed its topic to one you might be interested in and has been added to your list '" + event.getNews().getTitle() + "'");
                }
                else {
                    newsConsumerService.updateNewsIfNotDomainInterest(event.getNews());
                }
                break;
            case DELETE:
                if(domainInterest.equals(event.getNews().getDomain())) {
                    newsConsumerService.deleteNews(event.getNews());
                    System.out.println("A news you are interested in has been deleted '" + event.getNews().getTitle() + "'");
                }
                break;
        }
    }

    public NewsConsumerService getNewsConsumerService() {
        return newsConsumerService;
    }
}
