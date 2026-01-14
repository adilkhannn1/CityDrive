package citydrive.platform.news.service;

import citydrive.platform.news.dto.Request.NewsRequest;
import citydrive.platform.news.dto.Response.NewsResponse;
import citydrive.platform.news.mapper.NewsMapper;
import citydrive.platform.news.repository.NewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;


    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper){
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
    }

    public NewsResponse createNews(NewsRequest newsRequest){
        var entity = newsMapper.toEntity(newsRequest);
        newsRepository.save(entity);
        return newsMapper.toNewsResponse(entity);
    }


    public List<NewsResponse> getAllNews(){
        var news = newsRepository.findAll();
        return newsMapper.toListNewsResponse(news);
    }



    public void deleteNews(Long id){
        var news = newsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The news is not found by this id!"));
        newsRepository.delete(news);
    }



    public NewsResponse updateNews(NewsRequest newsRequest, Long id){
        var news = newsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The news is not found by this id!"));
        if(newsRequest.title()!=null){
            news.setTitle(newsRequest.title());
        }
        if(newsRequest.description() != null){
            news.setDescription(newsRequest.description());
        }
        if(newsRequest.photoPath() != null){
            news.setPhotoPath(newsRequest.photoPath());
        }
        newsRepository.save(news);
        return newsMapper.toNewsResponse(news);
    }



    public NewsResponse getNews(Long id){
        var news = newsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "The news is not found by this id!"));
        return newsMapper.toNewsResponse(news);
    }




}
