import java.util.List;

public interface Scrapper {
    List<JobPosting> scrape(SearchConfig searchConfig);
}
