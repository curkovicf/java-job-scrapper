import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

import java.util.ArrayList;
import java.util.List;


public class PosaoHrScrapper extends WebScrapper {
    private final String URL = "https://www.posao.hr/poslovi/izraz/";
    private int currPage = 1;

    public List<PostedJob> scrape(String jobDesc) {
        List<String> data = new ArrayList<>();

        try {
            Element current = null;
            do {
                Document doc = Jsoup.connect(this.URL + jobDesc + "/stranica/" + this.currPage).get();
                Elements postingContainer = doc.getElementsByClass("search_grid");

                this.extractData(postingContainer, data);

                current = doc.getElementsByClass("current").first();
                this.currPage++;
            } while (current != null && current.hasText());
        } catch (Exception e) {e.printStackTrace();}

        return this.constructModel(data);
    }

    private void extractData(Elements postingContainer, List<String> data) {

        postingContainer.traverse(new NodeVisitor() {
            @Override
            public void head(Node node, int i) {
                if (node.parent().hasAttr("href")) {
                    data.add(node.parent().attr("href"));
                } else if (node.hasAttr("#text") && !node.attr("#text").equals("(HZZ oglas)")) {
                    data.add(node.attr("#text"));
                }
            }

            @Override
            public void tail(Node node, int i) {

            }
        });
    }

    private List<PostedJob> constructModel(List<String> data) {
        List<PostedJob> models = new ArrayList<>();

        PostedJob job = null;
        List<String> objHelperList = new ArrayList<>();
        for (int index = 0; index < data.size(); index++) {
            objHelperList.add(data.get(index));

            if (index % 5 == 0 && index != 0) {
                models.add(new PostedJob(
                        objHelperList.get(0),
                        objHelperList.get(1),
                        objHelperList.get(2),
                        objHelperList.get(3),
                        objHelperList.get(4)
                ));
                objHelperList.clear();
            }
        }

        return models;
    }
}
