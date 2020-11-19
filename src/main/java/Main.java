import model.Editor;
import model.News;

public class Main {
    public static void main(String[] args) {
        News news = new News("very true news pisici", "zoo", new Editor("honest writer"), 2);
        System.out.println(news.toString());
    }

}
