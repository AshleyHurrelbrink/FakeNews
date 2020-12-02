package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import org.joda.time.DateTime;

public class News implements Serializable {
    private int id;
    private String title;
    private String domain;
    private String author;
    private Date publishDate;
    private int numberOfReads;

    public News(int id, String title, String domain, String author, int pushBackDays) {
        this.id = id;
        this.title = title;
        this.domain = domain;
        this.author = author;
        publishDate = new DateTime(new Date()).minusDays(pushBackDays).toDate();
        numberOfReads = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumberOfReads() {
        return numberOfReads;
    }

    public void setNumberOfReads(int numberOfReads) {
        this.numberOfReads = numberOfReads;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", author=" + author +
                ", publishDate=" + publishDate +
                ", numberOfReads=" + numberOfReads +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                numberOfReads == news.numberOfReads &&
                Objects.equals(title, news.title) &&
                Objects.equals(domain, news.domain) &&
                Objects.equals(author, news.author) &&
                Objects.equals(publishDate, news.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, domain, author, publishDate, numberOfReads);
    }

}

