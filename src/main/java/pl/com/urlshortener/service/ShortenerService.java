package pl.com.urlshortener.service;

import pl.com.urlshortener.model.UrlDto;

public interface ShortenerService {

    UrlDto shortenTheUrl(UrlDto url) throws ServiceException;
    
    String findByShortenUrl(String shortenUrl) throws ServiceException;
    
}