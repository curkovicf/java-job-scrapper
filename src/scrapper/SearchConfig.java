package scrapper;

public class SearchConfig {
    public int province;
    public String profession;
    public Search searchOn;

    public SearchConfig(int province, String profession, Search searchOn) {
        this.province = province;
        this.profession = profession;
        this.searchOn = searchOn;
    }

    public enum Search {
        POSAO_HR,
        MOJPOSAO_HR,
        ALL
    }
}
