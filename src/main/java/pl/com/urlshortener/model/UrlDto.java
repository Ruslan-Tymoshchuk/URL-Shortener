package pl.com.urlshortener.model;

public class UrlDto {

    private String originalUrl;
    private String shortenedUrl;
    private Integer clicks;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenUrl() {
        return shortenedUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenedUrl = shortenUrl;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}