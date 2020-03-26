import java.util.Map;

public class Provinces {
    private static Provinces provinces;
    private Map<String, String> provincesMap;

    private Provinces() {
    }

    public Map<String, String> getProvincesMap() {
        return provincesMap;
    }

    public void setProvincesMap(Map<String, String> provincesMap) {
        this.provincesMap = provincesMap;
    }

    public static Provinces getInstance() {
        if (provinces == null)
            provinces = new Provinces();

        return provinces;
    }
}
