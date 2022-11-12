package pl.com.urlshortener.controller;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.urlshortener.model.UrlDto;
import pl.com.urlshortener.service.ShortenerService;

@Controller
public class ShortenerController {

    private final ShortenerService shortenerService;
   
    public ShortenerController(ShortenerService shortenerService) {
        this.shortenerService = shortenerService;
    }

    @GetMapping
    public String showShortenerMain(Model model, UrlDto url) {
        return "shortener";
    }

    @PostMapping
    public String createShortenedLink(Model model, @Valid UrlDto url, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
                return "shortener";
            }
            model.addAttribute("urlDto", shortenerService.shortenTheUrl(url)); 
            return "shortened_url";
    }

    @GetMapping("/times")
    public String showTimesFollowedByLink(@RequestParam(name = "shortened_url") String shortenedUrl, Model model) {
            model.addAttribute("times", shortenerService.getTimesFollowedByLink(shortenedUrl));
        return "clicks";
    }

    @GetMapping("/{shortened_url}")
    public RedirectView viewByShortenedUrl(@PathVariable("shortened_url") String shortenedUrl) { 
            return new RedirectView(shortenerService.findByShortenedUrl(shortenedUrl));
    }
}