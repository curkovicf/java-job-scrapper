import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobService postings = new JobService();
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
