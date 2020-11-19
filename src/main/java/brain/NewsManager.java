package brain;

import model.Editor;
import model.News;
import rx.Observable;
import service.NewsService;

import java.util.ArrayList;

public class NewsManager {
    private ArrayList<News> news = new ArrayList<News>();
    private Editor editor = new Editor("honest writer");
    private NewsService newsService;

    public void init() {
        news.add(new News("very true news pisici", "zoo", editor, 2));
        news.add(new News("something about politics", "politics", editor, 2));
        news.add(new News("soc soc si groaza", "misc", editor, 2));
        news.add(new News("very true news catei", "zoo", editor, 2));
    }

    public void doTheJob() {
        Observable<News> editor = newsService.getData(news, "editor");
        Observable<News> reader1 = newsService.getData(news, "reader");
        Observable<News> reader2 = newsService.getData(news, "reader");
        editor.subscribe(System.out::println,
                throwable -> System.out.println("Exception" + throwable),
                () -> System.out.println("Editor is done"));
        reader1.subscribe(System.out::println,
                throwable -> System.out.println("Exception" + throwable),
                () -> System.out.println("Reader1 is done"));
        reader2.subscribe(System.out::println,
                throwable -> System.out.println("Exception" + throwable),
                () -> System.out.println("Reader2 is done"));
    }
}
