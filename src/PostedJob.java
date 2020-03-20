public class PostedJob {
    private String url;
    private String description;
    private String location;
    private String expires;
    private String employer;

    public PostedJob(String url, String description, String location, String expires, String employer) {
        this.url = url;
        this.description = description;
        this.location = location;
        this.expires = expires;
        this.employer = employer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }
}
