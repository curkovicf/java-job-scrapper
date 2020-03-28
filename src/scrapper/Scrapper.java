package scrapper;

import java.util.List;

interface Scrapper {
    List<JobPosting> scrape(SearchConfig searchConfig);
}
