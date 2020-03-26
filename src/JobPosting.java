public class JobPosting {
    private String url;
    private String position;
    private String location;
    private String expires;
    private String employer;

    public JobPosting(String url, String position, String location, String expires, String employer) {
        this.url = url;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
