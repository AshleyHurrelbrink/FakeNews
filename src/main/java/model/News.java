package model;

import org.joda.time.DateTime;

import java.util.Date;

public class News {
    private String title;
    private String domain;
    private Editor author;
    private Date publishDate;
    private int numberOfReads;

    public News(String title, String domain, Editor author, int pushBackDays) {
        this.title = title;
        this.domain = domain;
        this.author = author;
        publishDate = new DateTime(new Date()).minusDays(pushBackDays).toDate();
        numberOfReads = 0;
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

    public Editor getAuthor() {
        return author;
    }

    public void setAuthor(Editor author) {
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
                "title='" + title + '\'' +
                ", domain='" + domain + '\'' +
                ", author=" + author +
                ", publishDate=" + publishDate +
                ", numberOfReads=" + numberOfReads +
                '}';
    }
}
