import java.util.List;

public interface Scrapper {
    public List<JobPosting> scrape(SearchConfig searchConfig);
}
