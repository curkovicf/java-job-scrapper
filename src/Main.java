import java.util.List;

public class Main {
    public static void main(String[] args) {
        JobPostings postings = new JobPostings();
        List<JobPosting> list = postings.getJobs(new SearchConfig(
                "",
                "developer",
                SearchConfig.Search.MOJPOSAO_HR
        ));

//        Provinces.getInstance().getProvincesMap().values().forEach(el -> {
//            System.out.println(el.toString());
//        });
//        for (JobPosting job : list) {
//            System.out.println(job.getEmployer());
//        }
    }
}
