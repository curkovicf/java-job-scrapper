import scrapper.JobPosting;
import scrapper.JobService;
import scrapper.SearchConfig;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobService postings = new JobService();
        List<JobPosting> list = postings.getJobs(new SearchConfig(
                0,
                "developer",
                SearchConfig.Search.ALL
        ));

        for (JobPosting job : list) {
            System.out.println(job.getLocation());
        }
    }
}
