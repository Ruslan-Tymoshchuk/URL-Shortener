package pl.com.urlshortener.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UrlDto {

    private String hostName;
    @NotEmpty(message = "Original Url cannot be empty")
    @Size(message = "Size must be between from 10 to 250 characters", min = 10, max = 250)
    private String originalUrl;
    private String shortenedUrl;
    private Integer clicks;
    
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortenedUrl() {
        return shortenedUrl;
    }

    public void setShortenedUrl(String shortenUrl) {
        this.shortenedUrl = shortenUrl;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }
}