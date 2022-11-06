package pl.com.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.urlshortener.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

}