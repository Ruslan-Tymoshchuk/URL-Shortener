package pl.com.urlshortener.service;

@SuppressWarnings("serial")
public class ServiceException extends Exception {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}