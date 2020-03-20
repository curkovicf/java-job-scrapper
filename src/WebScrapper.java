import java.util.List;

public abstract class WebScrapper {

    public abstract List<PostedJob> scrape(String jobDesc);
}
