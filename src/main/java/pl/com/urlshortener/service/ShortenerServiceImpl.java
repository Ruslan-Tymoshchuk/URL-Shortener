package pl.com.urlshortener.service;

import org.springframework.stereotype.Service;
import pl.com.urlshortener.repository.UrlRepository;

@Service
public class ShortenerServiceImpl implements ShortenerService {

    private final UrlRepository urlRepository;
    
    public ShortenerServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
}