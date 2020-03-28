package scrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

import java.util.ArrayList;
import java.util.List;


class PosaoHrScrapper extends WebScrapper implements Scrapper {
    private final String URL = "https://www.posao.hr/poslovi/izraz/";
    private final PosaoHrProvinces provinces = new PosaoHrProvinces();


    public List<JobPosting> scrape(SearchConfig searchConfig) {
        List<String> data = new ArrayList<>();

        try {
            Element current = null;
            do {
                Document doc = Jsoup.connect(this.buildURL(searchConfig)).get();
                Elements postingContainer = doc.getElementsByClass("search_grid");

                this.extractData(postingContainer, data);

                current = doc.getElementsByClass("current").first();
                this.currPage++;
            } while (current != null && current.hasText());
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.currPage = 1;

        return this.createJobList(data);
    }

    private String buildURL(SearchConfig searchConfig) {
        String zupanija = "/zupanija/" + provinces.get(searchConfig.province);
        return this.URL + searchConfig.profession +
                (zupanija.equals("/zupanija/") ? "" : zupanija) +
                "/stranica/" + this.currPage;
    }


    private void extractData(Elements postingContainer, List<String> data) {
        List<String> tempArr = new ArrayList<>();

        postingContainer.traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int i) {
                if (node.parent().hasAttr("href")) {
                    data.add(node.parent().attr("href"));
                    tempArr.add(node.parent().attr("href"));
                } else if (node.hasAttr("#text") && !node.attr("#text").equals("(HZZ oglas)")) {
                    data.add(node.attr("#text"));
                    tempArr.add(node.parent().attr("href"));
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
                tempList.get(2),
                tempList.get(3),
                tempList.get(4)
        );
    }
}
