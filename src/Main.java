import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobPostings postings = new JobPostings();
        List<JobPosting> list = postings.getJobs(new SearchConfig(
                5,
                "developer",
                SearchConfig.Search.ALL
        ));

        for (JobPosting job : list) {
            System.out.println(job.getLocation());
        }
    }
}
