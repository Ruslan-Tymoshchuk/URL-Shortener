package pl.com.urlshortener.service;

import javax.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.com.urlshortener.exception.ServiceException;
import pl.com.urlshortener.model.Url;
import pl.com.urlshortener.model.UrlDto;
import pl.com.urlshortener.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional(readOnly = true)
public class ShortenerServiceImpl implements ShortenerService {

    public static final Logger logger = LoggerFactory.getLogger(ShortenerServiceImpl.class);

    private final UrlRepository urlRepository;
   
    public ShortenerServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    @Transactional
    public UrlDto shortenTheUrl(UrlDto url) {
        logger.debug("Shortening the url");
        try {
            Url urlEntity = new Url();
            urlEntity.setOriginalUrl(url.getOriginalUrl());
            String shortenedUrl = RandomStringUtils.randomAlphanumeric(5);
            urlEntity.setShortenedUrl(shortenedUrl);
            urlEntity.setClicks(0);
            urlEntity = urlRepository.save(urlEntity);
            url.setOriginalUrl(urlEntity.getOriginalUrl());
            url.setShortenedUrl(urlEntity.getShortenedUrl());
        } catch (DataAccessException e) {
            logger.error("Shortening the url failed");
            throw new ServiceException("Repository error occured", e);
        }
        return url;
    }

    @Override
    @Transactional
    public String findByShortenedUrl(String shortenedUrl) {
        logger.debug("Find by shorten url = {}", shortenedUrl);
        try {
            Url url = urlRepository.findByShortenedUrl(shortenedUrl)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + shortenedUrl + " url"));
            url.setClicks(url.getClicks() + 1);
            return url.getOriginalUrl();
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occurred", e);
        }
    }
    
    @Override
    public Integer getTimesFollowedByLink(String shortenedUrl) {
        logger.debug("Get times followed by linck = {}", shortenedUrl);
        try {
        return urlRepository.findByShortenedUrl(shortenedUrl)
                .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by " + shortenedUrl + " url")).getClicks();       
        }catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occurred", e);
        }
    }
}