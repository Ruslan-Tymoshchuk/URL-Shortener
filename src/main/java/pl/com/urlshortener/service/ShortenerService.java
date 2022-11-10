package pl.com.urlshortener.service;

import pl.com.urlshortener.model.UrlDto;

public interface ShortenerService {

    UrlDto shortenTheUrl(UrlDto url) throws ServiceException;
    
    String findByShortenedUrl(String shortenUrl) throws ServiceException;
    
    Integer getTimesFollowedByLink(String shortenUrl) throws ServiceException;
    
}