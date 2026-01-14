package citydrive.platform.admin.controller;

import citydrive.platform.news.dto.Request.NewsRequest;
import citydrive.platform.news.dto.Response.NewsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import citydrive.platform.news.service.NewsService;

import java.util.List;


@RestController
@RequestMapping("/admin/news")
public class AdminNewsController {

    private final NewsService newsService;

    public AdminNewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping
    public ResponseEntity<NewsResponse> createNews(@RequestBody NewsRequest newsRequest){
        var news = newsService.createNews(newsRequest);
        return ResponseEntity.ok(news);
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

    @GetMapping
    public ResponseEntity<List<NewsResponse>> getAllNews(){
        return ResponseEntity.ok(newsService.getAllNews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> getAllNews(@PathVariable Long id){
        return ResponseEntity.ok(newsService.getNews(id));
    }


}
