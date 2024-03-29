package scrapper;

import java.util.ArrayList;
import java.util.List;

abstract class WebScrapper {
    protected int currPage = 1;

    protected List<JobPosting> createJobList(List<String> data) {
        List<JobPosting> models = new ArrayList<>();

        JobPosting job = null;
        List<String> objHelperList = new ArrayList<>();
        for (int index = 0; index < data.size(); index++) {
            objHelperList.add(data.get(index));

            if (objHelperList.size() % 5 == 0 && index != 0) {
                models.add(createJob(objHelperList));
                objHelperList.clear();
            }
        }

        return models;
    }

    protected JobPosting createJob(List<String> tempList) {
        return null;
    }
}
