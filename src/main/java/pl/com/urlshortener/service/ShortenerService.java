package pl.com.urlshortener.service;

import pl.com.urlshortener.model.UrlDto;

public interface ShortenerService {

    UrlDto shortenTheUrl(UrlDto url);
    
    String findByShortenedUrl(String shortenUrl);
    
    Integer getTimesFollowedByLink(String shortenUrl);
    
}