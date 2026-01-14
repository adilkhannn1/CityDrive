package citydrive.platform.user.controller;

import citydrive.platform.news.dto.Request.NewsRequest;
import citydrive.platform.news.dto.Response.NewsResponse;

import citydrive.platform.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/news")
public class UserNewsController {
    private final NewsService newsService;
    public UserNewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest){
        var news = newsService.createNews(newsRequest);
        return ResponseEntity.ok(news);
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getNews(@PathVariable Long id){
        return ResponseEntity.ok(newsService.getNews(id));
    }



}
