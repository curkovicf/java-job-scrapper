import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MojPosaoScrapper extends WebScrapper implements Scrapper {
    private final String URL = "https://www.moj-posao.net/Pretraga-Poslova/";

    @Override
    public List<JobPosting> scrape(SearchConfig searchConfig) {
        List<String> data = new ArrayList<>();

        try {
            Element current = null;
            do {
                if (Provinces.getInstance().getProvincesMap() == null) {
                    Provinces.getInstance().setProvincesMap(this.getProvinces());
                }

                Document doc = Jsoup.connect(this.buildURL(searchConfig)).get();
                Element searchList = doc.getElementsByClass("searchlist").first();


                this.extractData(searchList, data);

                current = doc.getElementsByClass("active").last();
                this.currPage++;
            } while (!current.nextElementSibling().hasClass("unavailable"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.currPage = 1;

        return this.createJobList(data);
    }

    private String buildURL(SearchConfig searchConfig) {
        String areaKey = Provinces.getInstance().getProvincesMap().get(searchConfig.province);
        System.out.println();
        return this.URL +
                "?searchWord=" + searchConfig.profession +
                "&keyword=" + searchConfig.profession +
                "&job_title=&job_title_id=&area=" + (areaKey == null ? "" : areaKey) +
                "&category=&page=" + this.currPage;
    }

    private void extractData(Element searchlist, List<String> data) {
        Elements jobList = searchlist.getElementsByClass("job-data");

        jobList.traverse(new NodeVisitor() {

            @Override
            public void head(Node node, int i) {
                switch (node.attr("class")) {
                    case "job-title":
                        data.add(node.childNode(1).attr("href"));
                        data.add(((Element) node.childNode(1)).text());
                        break;
                    case "job-location":
                        data.add(((Element) node).text());
                        break;
                    case "job-company":
                        data.add(((Element) node.childNode(0)).text());
                        break;
                    case "deadline":
                        data.add(node.childNode(1).attr("datetime"));
                        break;
                }
            }

            @Override
            public void tail(Node node, int i) {

            }
        });
    }

    @Override
    protected JobPosting createJob(List<String> tempList) {
        return new JobPosting(
                tempList.get(0),
                tempList.get(1),
                tempList.get(3),
                tempList.get(4),
                tempList.get(2)
        );
    }

    private Map<String, String> getProvinces() throws IOException {
        Document doc = Jsoup.connect(this.URL).get();
        Map<String, String> provinceMap = new HashMap<>();
        Element select = doc.getElementById("form_mainsearch_area");

        select.children().traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int i) {
                if (node instanceof Element) {
                    String province_HR = ((Element) node).text().toLowerCase();
                    province_HR = Normalizer.normalize(province_HR, Normalizer.Form.NFD);
                    province_HR = province_HR.replaceAll("[^\\p{ASCII}]", "");
                    provinceMap.put(
                            province_HR,
                            node.attr("value")
                    );
                    System.out.println(province_HR);
                    System.out.println(node.attr("value"));
                }
            }

            @Override
            public void tail(Node node, int i) {

            }
        });
        provinceMap.put("", "");
        return provinceMap;
    }
}
