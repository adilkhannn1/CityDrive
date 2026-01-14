package citydrive.platform.news.mapper;

import citydrive.platform.news.dto.Request.NewsRequest;
import citydrive.platform.news.dto.Response.NewsResponse;
import citydrive.platform.news.entity.NewsEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsMapper {
    public NewsEntity toEntity(
            NewsRequest newsRequest
    ){
        return new NewsEntity(
                newsRequest.title(),
                newsRequest.description(),
                newsRequest.photoPath()
        );
    }

    public NewsResponse toNewsResponse(
            NewsEntity newsEntity
    ){
        return new NewsResponse(
                newsEntity.getId(),
                newsEntity.getTitle(),
                newsEntity.getDescription(),
                newsEntity.getPhotoPath(),
                newsEntity.getCreatedAt()
        );
    }

    public List<NewsResponse> toListNewsResponse(
            List<NewsEntity> newsEntities
    ){
        return newsEntities.stream().map(
                newsEntity -> new NewsResponse(
                        newsEntity.getId(),
                        newsEntity.getTitle(),
                        newsEntity.getDescription(),
                        newsEntity.getPhotoPath(),
                        newsEntity.getCreatedAt()
                )
        ).toList();
    }



}
