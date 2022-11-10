package pl.com.urlshortener.service;

import javax.persistence.EntityNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public UrlDto shortenTheUrl(UrlDto url) throws ServiceException {
        logger.debug("shortening the url");
        try {
            Url urlEntity = new Url();
            urlEntity.setOriginalUrl(url.getOriginalUrl());
            String shortenUrl = RandomStringUtils.randomAlphanumeric(5);
            urlEntity.setShortenUrl(shortenUrl);
            urlEntity.setClicks(0);
            urlEntity = urlRepository.save(urlEntity);
            url.setOriginalUrl(urlEntity.getOriginalUrl());
            url.setShortenUrl(urlEntity.getShortenUrl());
        } catch (DataAccessException e) {
            logger.error("Getting all group failed");
            throw new ServiceException("Repository error occured", e);
        }
        return url;
    }

    @Override
    @Transactional
    public String findByShortenedUrl(String shortenUrl) throws ServiceException {
        logger.debug("Find by shorten url = {}", shortenUrl);
        try {
            Url url = urlRepository.findByShortenUrl(shortenUrl)
                    .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by shorten url"));
            url.setClicks(url.getClicks() + 1);
            return url.getOriginalUrl();
        } catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occurred", e);
        }
    }
    
    @Override
    public Integer getTimesFollowedByLink(String shortenUrl) throws ServiceException {
        try {
        return urlRepository.findByShortenUrl(shortenUrl)
                .orElseThrow(() -> new EntityNotFoundException("Error occurred when searching by shorten url")).getClicks();       
        }catch (DataAccessException | EntityNotFoundException e) {
            throw new ServiceException("Repository error occurred", e);
        }
    }
}