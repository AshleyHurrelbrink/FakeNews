package service;

import model.News;
import rx.Observable;

import java.util.ArrayList;

public class NewsService {

    public Observable<News> getData(ArrayList<News> news, String type) {
        if(type.equals("editor")) {
            return editorGetData(news);
        }
        else if(type.equals("reader")) {
            return readerGetData(news);
        }
        return null;
    }

    private Observable<News> readerGetData(ArrayList<News> news) {
        return null;
    }

    private Observable<News> editorGetData(ArrayList<News> news) {
        return null;
    }
}
