package citydrive.platform.news.controller;

import citydrive.platform.news.dto.Request.NewsRequest;
import citydrive.platform.news.dto.Response.NewsResponse;
import citydrive.platform.news.entity.NewsEntity;
import citydrive.platform.news.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    public NewsController(NewsService newsService){
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getAllNews(@PathVariable Long id){
        return ResponseEntity.ok(newsService.getNews(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id){
         newsService.deleteNews(id);
         return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NewsResponse> updateNews(@RequestBody NewsRequest newsRequest, @PathVariable Long id){
        var news = newsService.updateNews(newsRequest, id);
        return ResponseEntity.ok(news);
    }


}
