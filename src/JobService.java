import java.util.List;

public class JobService {
    private Scrapper posaoHr = new PosaoHrScrapper();
    private Scrapper mojPosaoHr = new MojPosaoScrapper();


    public List<JobPosting> getJobs(SearchConfig searchConfig) {
        List<JobPosting> jobPostings = null;

        switch (searchConfig.searchOn) {
            case MOJPOSAO_HR:
                jobPostings = this.mojPosaoHr.scrape(searchConfig);
                break;
            case POSAO_HR:
                jobPostings = this.posaoHr.scrape(searchConfig);
                break;
            case ALL:
                jobPostings = this.mojPosaoHr.scrape(searchConfig);
                jobPostings.addAll(this.posaoHr.scrape(searchConfig));
                break;
        }

        return jobPostings;
    }
}
