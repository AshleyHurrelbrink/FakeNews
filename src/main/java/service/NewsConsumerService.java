package service;

import model.News;
import java.util.concurrent.CopyOnWriteArrayList;

public class NewsConsumerService {
    private CopyOnWriteArrayList<News> news = new CopyOnWriteArrayList<News>();

    public void addNews(News news) {
        this.news.add(news);
    }

    public void updateNewsIfDomainInterest(News news) {
        addNews(news);
    }

    public void updateNewsIfNotDomainInterest(News news) {
        int i = 1;
        for(News n : this.news) {
            if(n.getId() == news.getId()) {
                this.news.remove(i);
                System.out.println("News just changed its topic to one you are not interested in and has been removed from your list '" + news.getTitle() + "'");
            }
        }
    }

    public void deleteNews(News news) {
        this.news.remove(news);
    }

    public CopyOnWriteArrayList<News> getNews() {
        return news;
    }
}
