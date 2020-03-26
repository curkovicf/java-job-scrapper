import java.util.List;

public class Main {
    public static void main(String[] args) {
        PosaoHrScrapper scrapper = new PosaoHrScrapper();
        MojPosaoScrapper mojPosaoScrapper = new MojPosaoScrapper();
//        List<JobPosting> list = scrapper.scrape("developer");
        List<JobPosting> list = mojPosaoScrapper.scrape("developer");
//        for (JobPosting job : list) {
//            System.out.println(job.getEmployer());
//        }
    }
}
